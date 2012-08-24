package com.cc.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;

public class StringUtils {
	private static int ROW_NUM = 0;			
	private static int COL_NUM = 0;				//每行可显示多少个英文字母
	private static int MAX_WORD_NUM = 0;
	private static int SCREEN_WIDTH = 0;
	private static int SCREEN_HEIGHT = 0;
	private static DisplayMetrics displayMetrics;
	
	/** 
     * 半角转换为全角,字母数字没有转换 
     *  
     * @param input 
     * @return 
     */  
	public static String ToSBC(String input) {   
        char c[] = input.toCharArray();   
        for (int i = 0; i < c.length; i++) {   
          if (c[i] == ' ') {   
             c[i] = '\u3000';                 //采用十六进制,相当于十进制的12288   
 
 
           } else 
    	if ((c[i] < 127&&c[i] >=123)||(c[i]>=91&&c[i]<=96)||(c[i]>=58&&c[i]<=64)||(c[i]<=47&&c[i]>32)) {   
             c[i] = (char) (c[i] + 65248);   
           }
         }   
        return new String(c);   
	} 
	
	/**
	 * Split string return List<String>,base on can show how many words in one page(TextView),Best use in Chinese.
	 * @param s the string will be split 
	 * 		  ,context:activity context
	 * @return 
	 */
	public static List<String> split(String s,Context context,float fontSize,int lineSpacing){
		displayMetrics = context.getResources().getDisplayMetrics();
    	SCREEN_WIDTH = displayMetrics.widthPixels;
    	SCREEN_HEIGHT =displayMetrics.heightPixels;
    	ROW_NUM = (int)((SCREEN_HEIGHT-5)/(fontSize+lineSpacing)-1);
    	COL_NUM = ((int) ((SCREEN_WIDTH)/fontSize))*2;
    	MAX_WORD_NUM = ROW_NUM*COL_NUM;
		
        char[] c = s.toCharArray();
		List<String> list = new ArrayList<String>();
		int tmpLineCount = 0;
		int tmpWordLength = 0;
		int remainCount = MAX_WORD_NUM;
		int thisPageWordCount = 0;
		for(int i=0;i<c.length;i++){
		 	 if((c[i]>='\u4e00'&&c[i]<='\u9fa5')||c[i]>=65248||c[i]==12288||c[i]==12290){
		 		 tmpWordLength+=2;
		 		 remainCount -=2;
			 }else{
				 tmpWordLength++;
				 remainCount--;
			 }
			 if(c[i]==10){
				 int  tmp  = tmpWordLength/COL_NUM+1;
				 tmpLineCount += tmp;
				 System.out.println("tmpWordLength,COL_NUM,tmpLineCount:"+tmpWordLength+","+COL_NUM+","+tmpLineCount);
		 		 tmpWordLength = 0;
		 		 remainCount = MAX_WORD_NUM - tmpLineCount*COL_NUM;
		 	 }
		 	 if(tmpLineCount == ROW_NUM||remainCount==0||i==c.length-1){
		 		 if(i==c.length-1){
		 			list.add(s.substring(i-thisPageWordCount, i+1));
		 		 }else{
		 			list.add(s.substring(i-thisPageWordCount, i));
		 		 }
		 		 tmpLineCount = 0;
		 		 tmpWordLength = 0;
		 		 thisPageWordCount = 0;
		 		 remainCount = MAX_WORD_NUM;
		 	 }
		 	thisPageWordCount++;
		}
		return list;
	}
}
