package com.cc.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	
	/**
	 * 以post方式接收服务器发回信息
	 * @param URL 请求地址
	 * @param p 要传递的参数{{key,value},.....}
	 * @return
	 */
	public static String postReceive(String URL,String[][] p){
		String r = null;
		//HttpPost连接对象  
        HttpPost httpRequest = new HttpPost(URL); 
        //使用NameValuePair来保存要传递的Post参数  
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //取出要传递的参数
        if(p.length!=0&&p!=null){
        	for(int i=0;i<p.length;i++){
            	//添加要传递的参数  
                params.add(new BasicNameValuePair(p[i][0], p[i][1]));
            }
        }else{
        	params.add(new BasicNameValuePair("", ""));
        }
        //设置字符集  
        HttpEntity httpentity;
		try {
			httpentity = new UrlEncodedFormEntity(params, "utf8");
			//请求httpRequest  
            httpRequest.setEntity(httpentity);  
            //取得默认的HttpClient  
            HttpClient httpclient = new DefaultHttpClient();  
            //取得HttpResponse  
            HttpResponse httpResponse = httpclient.execute(httpRequest);  
            //HttpStatus.SC_OK表示连接成功  
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
                //取得返回的字符串  
                r = EntityUtils.toString(httpResponse.getEntity());
            }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}  
		return r;
	}
}
