package com.ekankhek.ekankhek.helper;

import java.text.SimpleDateFormat;

public class CommonHelper {

	public static final String root_path = "./uploads/";
	
	public static SimpleDateFormat getDateFromat(String format) {
		return new SimpleDateFormat(format);
	}
}
