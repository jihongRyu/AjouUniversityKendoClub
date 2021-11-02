package com.multi.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Util {
	public static String FILE_UPLOAD_BASE="/upload";
	
	public static String textToDb(String s)
	{
		if(s==null)
			return "";
		
		String result;
		result = s.replace("\n", "<br>");
		return result;
	}
	
	public static String dbToText(String s)
	{
		if(s==null)
			return "";
		String result;
		result = s.replace("<br>", "\n");
		return result;
	}
	
	public static String nullToValue(Object obj, String value)
	{
		if(obj==null)
			return value;
		
		return (String)obj;
	}
	
	public static String nullTo(Object obj)
	{
		if(obj==null)
			return "";
		
		return (String)obj;
	}
	
	public static String encoding(Object obj)
	{
		if(obj==null)
			return "";
		
		String result="";
		try {
			result = URLDecoder.decode((String)obj, "utf-8").toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	 
}
