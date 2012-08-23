package com.my.utils;

import android.os.Environment;

public class FileUtil {
	/**
	 * 获取SD卡目录,如果SDcard存在返回SD卡目录，否则返回null
	 *   
	 */
	public String getSdcardDirectory(){
		// 判断SDCard是否存在   
		String status = Environment.getExternalStorageState();  
		boolean isSDCardExist = status.equals(Environment.MEDIA_MOUNTED);  
		//如果存在则获取SDCard目录    
		if(isSDCardExist){  
		    return Environment.getExternalStorageDirectory().getAbsolutePath();  
		}else{
			return null;
		}
	}
}
