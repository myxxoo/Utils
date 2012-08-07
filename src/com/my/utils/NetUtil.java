package com.my.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	/**
	 * 	检查网络是否可用
	 *
	 *	如果网络不通，可使用下面的服务进入手机的网络配置
	 *	startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)); 
	 * @param context
	 * @return
	 */
	 public static boolean checkNet(Context context){   
	     /*根据系统服务获取手机连接管理对象*/  
	     ConnectivityManager connectivity = (ConnectivityManager)context.   
	             getSystemService(Context.CONNECTIVITY_SERVICE);   
	     if(connectivity!=null){   
	         NetworkInfo info = connectivity.getActiveNetworkInfo();   
	         if(info!=null && info.isConnected()){      
	             //判断当前网络是否连接   
	             if(info.getState()==NetworkInfo.State.CONNECTED){   
	                 return true;   
	             }   
	         }   
	     }   
	     return false;   
	    }   
 
}
