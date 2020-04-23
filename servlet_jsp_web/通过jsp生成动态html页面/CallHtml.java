package com.zcq.qiaosoft.text;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class CallHtml {

	 public static void callOnePage(String fileName, String path,
	   String realName, String realPath,String method) {
	  try {
	   String str = "http://localhost:8080/Test11/ToHtml?file_name="
	     + fileName + "&&path=" + path + "&&realName=" + realName
	     + "&&realPath=" + realPath + "&&method="+method;
	   int httpResult;
	   URL url = new URL(str);
	   URLConnection connection = url.openConnection();
	   connection.connect();
	   HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
	   httpResult = httpURLConnection.getResponseCode();
	   if (httpResult != HttpURLConnection.HTTP_OK) {
	    System.out.println("没有连接成功");
	   } else {
	    System.out.println("连接成功");
	   }
	  }catch (Exception e) {
	   
	  }
	}
}

