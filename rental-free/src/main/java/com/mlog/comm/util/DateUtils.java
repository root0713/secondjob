package com.mlog.comm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public class DateUtils {
	public static final SimpleDateFormat FLAT_6 = new SimpleDateFormat("yyyyMM");
	public static final SimpleDateFormat FLAT_8 = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat FLAT_14 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat FLAT_17 = new SimpleDateFormat("yyyyMMddHHmmssSS");
	public static final SimpleDateFormat FORMAT_8 = new SimpleDateFormat("yyyy/MM/dd");
	public static final SimpleDateFormat FORMAT_14 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static final SimpleDateFormat JQ_UI_8 = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final FastDateFormat FAST_DATE_FORMAT_FLAT_14 = FastDateFormat.getInstance("yyyyMMddHHmmss", new Locale("ko_KR"));
	
	public static String getFlatDateStr(int digit) {
		return getFlatDateStr(new Date(), digit);
	}
	
	public static String getFlatDateStr(Date date, int digit) {
		if (digit == 6)
			return getDateStr(date, FLAT_6);
	
		if (digit == 8)
			return getDateStr(date, FLAT_8);
		
		if (digit == 17)
			return getDateStr(date, FLAT_17);
		
		return getDateStr(date, FLAT_14);
	}

	public static String getFrmtDateStr(int digit) {
		return getFrmtDateStr(new Date(), digit);
	}
	
	public static String getFrmtDateStr(Date date, int digit) {
		if (digit == 8)
			return getDateStr(date, FORMAT_8);
		return getDateStr(date, FORMAT_14);
	}
	
	public static String getDateStr(String fmt) {
		return getDateStr(new Date(), fmt);
	}
	
	public static String getDateStr(Date date, String fmt) {
		return getDateStr(date, new SimpleDateFormat(fmt));
	}

	public static String getDateStr(Date date, SimpleDateFormat formatter) {
		return formatter.format(date);
	}
	
	public static String flat2fmt(String dateStr) {
		try {
			return getFrmtDateStr(FLAT_14.parse(dateStr), 14);
		}
		catch (ParseException e) {
			return null;
		}
	}
	
	public static Date getDate8(String dateStr) {
		return getDate(dateStr, FLAT_8);
	}
	
	public static Date getDate14(String dateStr) {
		return getDate(dateStr, FLAT_14);
	}
	
	public static Date getDate(String dateStr, int digit) {
		try {
			if (digit == 8)
				return FORMAT_8.parse(dateStr);
			return FORMAT_14.parse(dateStr);
		}
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Date getDate(String dateStr, SimpleDateFormat fmt) {
		try {
			return fmt.parse(dateStr);
		}
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
//	public static DateTime getDateTime(String dateStr) {
//		return new DateTime(getDate(dateStr, FLAT_14));
//	}
	
	public static String jquiDate(String dateStr) {
		if (dateStr == null || "".equals(dateStr))
			return "";
		return getDateStr(getDate(dateStr, FLAT_14), JQ_UI_8);
	}
	
	public static String convertFLAT_14(String milliseconds) {
		if(StringUtils.isNotEmpty(milliseconds) && milliseconds.length() == 13){
			return FLAT_14.format(new Date(Long.valueOf(milliseconds)));
		}else{
			return milliseconds;
		}
	}
	
	public static long getUnixTime(String dateStr) {
		try {
			return FAST_DATE_FORMAT_FLAT_14.parse(dateStr).getTime();
			//return FLAT_14.parse(dateStr).getTime();
		}
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
