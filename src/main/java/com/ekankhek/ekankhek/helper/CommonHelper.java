package com.ekankhek.ekankhek.helper;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonHelper {

	public static final String root_path = "./uploads/";
	public static final String base_url = "http://localhost:9999/";
	
	public static SimpleDateFormat getDateFromat(String format) {
		return new SimpleDateFormat(format);
	}
	
	 static boolean useLetters = true;
	 static boolean useNumbers = false;
	    
	 String generatedString ;
	 
	 public static String generateString(int length) {
		 return RandomStringUtils.random(length, useLetters, useNumbers);
	 }

}
