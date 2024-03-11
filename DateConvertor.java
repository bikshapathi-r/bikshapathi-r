package com.ctel.tts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor 
{

	public static final String YYYYMMDD = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String YYYYMMDDHHMISS = "yyyyMMddHHmmss";
	public static String convertDateToString(Date date, String pattern)
	{
		String dateStr = "";
		if(date!= null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	public static Date convertStringToDate(String date, String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date _date = null;
		if(date!=null){
			try {
				_date = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _date;
	}

}

