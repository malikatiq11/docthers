package com.Patient.Utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class my_utility {
	static HttpClient httpClient;
	 static HttpPost httpPost;
	
	static public String hmsRequest(String path, ArrayList<NameValuePair> nvp) {
		  try {
		   httpClient = new DefaultHttpClient();
		   httpPost = new HttpPost("http://www.aqibbutt.com/Docthers/"+ path);
		   httpPost.setEntity(new UrlEncodedFormEntity(nvp));
		   HttpResponse response = httpClient.execute(httpPost);
		   Log.d("BUTT","Respone was "+response);
		   String result = hms_getStringFromResonse(response);
		   Log.d("BUTT","Result  was "+result);
		   return result;
		  }
		  catch(Exception ex) {
		   Log.e("IAS Connection Error 2:", ex.toString());
		   return null;
		  }
		 }
		 static public String hms_getStringFromResonse(HttpResponse response) {
		  try{
		   HttpEntity entity = response.getEntity();
		   InputStream is = entity.getContent();
		   BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),8);
		   StringBuilder sb = new StringBuilder();
		   String line;
		   while( (line = reader.readLine()) != null) {
		    sb.append(line);
		   }
		   return sb.toString();
		  }
		  catch(Exception ex) {
		   Log.e("HMS Connection Error 2:", ex.toString());
		   return null;
		  }
		 }
		 
		 	
		 }
