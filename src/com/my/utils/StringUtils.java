/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.my.utils;

import java.util.Map;


/**
 * Common string-related functions.
 *
 * @author Sean Owen
 */
public final class StringUtils {

  private static final String PLATFORM_DEFAULT_ENCODING =
      System.getProperty("file.encoding");
  public static final String GB2312 = "GB2312";
  private static final String EUC_JP = "EUC_JP";
  private static final String UTF8 = "UTF8";
  private static final String ISO88591 = "ISO8859_1";

  private StringUtils() {}

  /**
   * @param bytes bytes encoding a string, whose encoding should be guessed
   * @param hints decode hints if applicable
   * @return name of guessed encoding; at the moment will only guess one of:
   *  {@link #GB2312}, {@link #UTF8}, {@link #ISO88591}, or the platform
   *  default encoding if none of these can possibly be correct
   */
  public static String guessEncoding(byte[] bytes) {
    if (bytes.length > 3 &&
        bytes[0] == (byte) 0xEF &&
        bytes[1] == (byte) 0xBB &&
        bytes[2] == (byte) 0xBF) {
      return UTF8;
    }
    int length = bytes.length;
    boolean canBeISO88591 = true;
    boolean canBeUTF8 = true;
    int utf8BytesLeft = 0;
    int maybeDoubleByteCount = 0;
    boolean sawLatin1Supplement = false;
    boolean sawUTF8Start = false;
    boolean lastWasPossibleDoubleByteStart = false;

    for (int i = 0;
         i < length && (canBeISO88591  || canBeUTF8);
         i++) {

      int value = bytes[i] & 0xFF;

      // UTF-8 stuff
      if (value >= 0x80 && value <= 0xBF) {
        if (utf8BytesLeft > 0) {
          utf8BytesLeft--;
        }
      } else {
        if (utf8BytesLeft > 0) {
          canBeUTF8 = false;
        }
        if (value >= 0xC0 && value <= 0xFD) {
          sawUTF8Start = true;
          int valueCopy = value;
          while ((valueCopy & 0x40) != 0) {
            utf8BytesLeft++;
            valueCopy <<= 1;
          }
        }
      }
      
      // ISO-8859-1 stuff

      if ((value == 0xC2 || value == 0xC3) && i < length - 1) {
        // This is really a poor hack. The slightly more exotic characters people might want to put in
        // a QR Code, by which I mean the Latin-1 supplement characters (e.g. u-umlaut) have encodings
        // that start with 0xC2 followed by [0xA0,0xBF], or start with 0xC3 followed by [0x80,0xBF].
        int nextValue = bytes[i + 1] & 0xFF;
        if (nextValue <= 0xBF &&
            ((value == 0xC2 && nextValue >= 0xA0) || (value == 0xC3 && nextValue >= 0x80))) {
          sawLatin1Supplement = true;
        }
      }
      if (value >= 0x7F && value <= 0x9F) {
        canBeISO88591 = false;
      }
    }
    if (utf8BytesLeft > 0) {
      canBeUTF8 = false;
    }

    if (canBeUTF8 && sawUTF8Start) {
      return UTF8;
    }
    
    if(isGB2312(bytes)){
  	  return GB2312;
    }
    // Distinguishing Shift_JIS and ISO-8859-1 can be a little tough. The crude heuristic is:
    // - If we saw
    //   - at least 3 bytes that starts a double-byte value (bytes that are rare in ISO-8859-1), or
    //   - over 5% of bytes could be single-byte Katakana (also rare in ISO-8859-1),
    // - and, saw no sequences that are invalid in Shift_JIS, then we conclude Shift_JIS
    // Otherwise, we default to ISO-8859-1 unless we know it can't be
    if (!sawLatin1Supplement && canBeISO88591) {
      return ISO88591;
    }
    // Otherwise, we take a wild guess with platform encoding
    return PLATFORM_DEFAULT_ENCODING;
  }
  
  private static boolean isGB2312(byte[] b) {
      boolean isGB2312 = false;
      for (int i = 0; i < b.length-1 && b.length%2 == 0; i=i+2) {
              int[] ints = new int[2];
              ints[0] = b[i] & 0xff;
              ints[1] = b[i+1] & 0xff;
              if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
                      && ints[1] <= 0xFE) {
                  isGB2312 = true;
                  break;
              }
      }
      return isGB2312;
  }
  

}
