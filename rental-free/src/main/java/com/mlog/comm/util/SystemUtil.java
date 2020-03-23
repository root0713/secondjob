package com.mlog.comm.util;

import java.util.Enumeration;
import java.util.Properties;

public class SystemUtil {
	
	public static String getSystemProperty(String key) {

		String val = "";

		Properties props = System.getProperties();
		Enumeration<?> enums = props.keys();
		while (enums.hasMoreElements()) {
			String _key = (String) enums.nextElement();
			if (key.equals(_key)) {
				val = (String) props.get(key);
				break;
			}
		}
		
		return val;
	}

}
