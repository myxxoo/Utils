package com.cc.utils;

import android.util.Log;
/**
 * Log print
 * @author 	John
 *
 */
public class CCLog{

	private static String TAG = "CCLog";
	/**
	 * VERBOSE Log
	 * color:black
	 * @param Object o
	 */
	public static void v(Object o){
		if(o.equals(null)){
			e("null");
		}else{
			Log.v(TAG, o.toString());
		}
	}
	/**
	 * DEBUG Log
	 * color:blue
	 * @param Object o
	 */
	public static void d(Object o){
		if(o.equals(null)){
			e("null");
		}else{
			Log.d(TAG, o.toString());
		}
	}
	/**
	 * INFO Log
	 * color:green
	 * @param Object o
	 */
	public static void i(Object o){
		if(o.equals(null)){
			e("null");
		}else{
			Log.i(TAG, o.toString());
		}
	}
	/**
	 * WARN Log
	 * color:orange
	 * @param Object o
	 */
	public static void w(Object o){
		if(o.equals(null)){
			e("null");
			return;
		}
		Log.w(TAG, o.toString());
	}
	/**
	 * ERROR Log 
	 * color:red
	 * @param Object o
	 */
	public static void e(Object o){
		if(o.equals(null)){
			Log.e(TAG, "null");
		}else{
			Log.e(TAG, o.toString());
		}
	}
}
