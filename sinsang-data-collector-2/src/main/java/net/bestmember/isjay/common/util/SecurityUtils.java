package net.bestmember.isjay.common.util;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.UNICODE_CASE;

import java.security.MessageDigest;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtils {
	private static final Logger log = LoggerFactory.getLogger(SecurityUtils.class);

	private static Pattern p1 = Pattern.compile("<script>(.*?)</script>", CASE_INSENSITIVE);
	private static Pattern p2 = Pattern.compile("src[\r\n]*=[\r\n]*\\'(.*?)\\'", UNICODE_CASE | CASE_INSENSITIVE);
	private static Pattern p3 = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", UNICODE_CASE | CASE_INSENSITIVE);
	private static Pattern p4 = Pattern.compile("</script>", CASE_INSENSITIVE);
	private static Pattern p5 = Pattern.compile("<script(.*?)>", UNICODE_CASE | CASE_INSENSITIVE);
	private static Pattern p6 = Pattern.compile("eval\\((.*?)\\)", UNICODE_CASE | CASE_INSENSITIVE);
	private static Pattern p7 = Pattern.compile("expression\\((.*?)\\)", UNICODE_CASE | CASE_INSENSITIVE);
	private static Pattern p8 = Pattern.compile("javascript:", CASE_INSENSITIVE);
	private static Pattern p9 = Pattern.compile("vbscript:", CASE_INSENSITIVE);
	private static Pattern p10 = Pattern.compile("onload(.*?)=", UNICODE_CASE | CASE_INSENSITIVE);

	public static String cleanXSS(String paramString) {
		if (paramString == null || "".equals(paramString))
			return paramString;
		String str = paramString;
		str = p1.matcher(str).replaceAll("");
		str = p2.matcher(str).replaceAll("");
		str = p3.matcher(str).replaceAll("");
		str = p4.matcher(str).replaceAll("");
		str = p5.matcher(str).replaceAll("");
		str = p6.matcher(str).replaceAll("");
		str = p7.matcher(str).replaceAll("");
		str = p8.matcher(str).replaceAll("");
		str = p9.matcher(str).replaceAll("");
		str = p10.matcher(str).replaceAll("");
		str = str.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		str = str.replaceAll("\"", "&#34;").replaceAll("'", "&#39;").replaceAll("-", "&#45;");
		str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

		return str;
	}

	public static String recoverXSS(String str) {
		if (str == null || "".equals(str))
			return str;
		str = str.replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)");
		str = str.replaceAll("&#34;", "\"").replaceAll("&#39;", "'").replaceAll("&#45;", "-");
		str = str.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
		return str;
	}

	public static String md5Encrypt(String str) {
		String md5 = "";

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());

			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			return md5 = sb.toString();

		} catch (Exception e) {
			md5 = null;
			throw new RuntimeException(e);
		}
	}

}
