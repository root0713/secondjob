package net.bestmember.isjay.common.util;

import java.io.Reader;
import java.sql.Clob;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * (클래스 역할을 상세하게 기술한다.)
 * &#64;author Kenneth
 * &#64;since Nov 6, 2014 3:45:09 PM
 * &#64;version 1.0
 * &#64;Modification
 * 수정일			  수정자		수정내용
 * ----------- ---------- --------------------------------
 * Nov 6, 2014      Kenneth   최초생성
 * </pre>
 */
public class StringUtils {

	final static public String EMPTY_STR = "";

	/**
	 * null 또는 공백문자인지 확인한다.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object obj) {

		if (obj == null || obj.toString().trim().length() == 0) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}

		return false;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String nullToEmpty(String str) {
		if (str == null) {
			return "";
		}

		return str;
	}

	public static String nvl(String str, String val) {
		if (isEmpty(str)) {
			return val;
		}

		return str;
	}

	/**
	 * 입력된 문자열이 null 일경우 기본문자열을 리턴한다.
	 *
	 * @param str 문자열
	 * @param def 기본 문자열
	 * @return
	 */
	public static String checkNullToDefaultStr(String str, String def) {
		if (isEmpty(str))
			return def;
		return str;
	}

	public static String checkNullToDefaultStr(Object str, String def) {
		if (isEmpty(str))
			return def;
		return str.toString();
	}

	public static String checkNullToZero(String str) {
		if (isEmpty(str))
			return "0";
		else
			return str;
	}

	public static String checkNullToZero(Object str) {
		if (isEmpty(str))
			return "0";
		else
			return str.toString();
	}

	public static String repeat(char ch, int repeat) {
		char[] buf = new char[repeat];

		for (int i = repeat - 1; i >= 0; --i) {
			buf[i] = ch;
		}

		return new String(buf);
	}

	public static String leftPad(String str, int size) {
		return leftPad(str, size, ' ');
	}

	public static String leftPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		} else {
			int pads = size - str.length();
			return pads <= 0 ? str : (pads > 8192 ? leftPad(str, size, String.valueOf(padChar)) : repeat(padChar, pads).concat(str));
		}
	}

	public static String leftPad(String str, int size, String padStr) {
		if (str == null) {
			return null;
		} else {
			if (isEmpty(padStr)) {
				padStr = " ";
			}

			int padLen = padStr.length();
			int strLen = str.length();
			int pads = size - strLen;
			if (pads <= 0) {
				return str;
			} else if (padLen == 1 && pads <= 8192) {
				return leftPad(str, size, padStr.charAt(0));
			} else if (pads == padLen) {
				return padStr.concat(str);
			} else if (pads < padLen) {
				return padStr.substring(0, pads).concat(str);
			} else {
				char[] padding = new char[pads];
				char[] padChars = padStr.toCharArray();

				for (int i = 0; i < pads; ++i) {
					padding[i] = padChars[i % padLen];
				}

				return (new String(padding)).concat(str);
			}
		}
	}

	/*
	 * 확장자를 비교해서 같으면 true를 리턴 2016-03-16
	 * 
	 * @param patternStr String
	 * 
	 * @param extName String
	 * 
	 * @return boolean
	 */
	public static boolean findExtName(String patternStr, String extName) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(extName);

		return matcher.matches();
	}

	/**
	 * @Description Exception을 Html으로 출력
	 */
	public static String printStackTraceToHtml(Throwable e) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(e.toString());
		buffer.append("<br/>");
		StackTraceElement element[] = e.getStackTrace();
		for (StackTraceElement stack : element) {
			buffer.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("at").append("&nbsp;").append(stack.toString()).append("<br/>");
		}
		return buffer.toString();
	}

	/**
	 * @Description 입력값이 null일경우 "" 리턴
	 */
	public static String getString(Object str) {
		return getString(str, EMPTY_STR);
	}

	/**
	 * @Description 입력값이 없을경우 기본값 리턴
	 */
	public static String getString(Object str, String defaultStr) {
		if (str == null) {
			return defaultStr;
		} else {
			String value = null;
			if (str instanceof String) {
				value = (String) str;
			} else if (str instanceof Clob) {
				value = clob2String((Clob) str);
			} else {
				value = String.valueOf(str);
			}

			return isEmpty(value) ? defaultStr : value;
		}
	}

	/**
	 * @Description clob 변환
	 */
	public static String clob2String(Clob clob) {
		try {
			StringBuffer sb = new StringBuffer();
			Reader reader = clob.getCharacterStream();

			char[] buff = new char[1024];
			int nchars = 0;

			while ((nchars = reader.read(buff)) > 0) {
				sb.append(buff, 0, nchars);
			}

			return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}
}
