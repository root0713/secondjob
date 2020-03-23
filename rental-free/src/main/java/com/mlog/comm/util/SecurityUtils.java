package com.mlog.comm.util;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.UNICODE_CASE;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.mlog.comm.util.Aes128;
import com.mlog.comm.util.Aes256;

public class SecurityUtils {
	private static final Logger log = LoggerFactory.getLogger(SecurityUtils.class);
	private static final String AES_KEY = "/com/mlog/config/aes256.key";
	private static final String AES128_KEY = "/com/mlog/config/aes128.key";
	private static final String AES_STR_CHARSET = "UTF-8";
	private static final String DEFAULT_DELIMITER = "\b";

	public static UserDetails getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || "anonymousUser".equals(auth.getPrincipal().toString()))
			return null;
		
		return (UserDetails) auth.getPrincipal();
	}

	public static String getCurrentUserName() {
		UserDetails user = getCurrentUser();
		return user == null ? null : user.getUsername();
	}
	
	public static boolean hasRole(String... roles) {
		UserDetails user = getCurrentUser();
		if (user == null) return false;
		
		List<String> aroles = Arrays.asList(roles);
		for (GrantedAuthority auth : user.getAuthorities()) {
			if (aroles.contains(auth.toString()))
				return true;
		}

		return false;
	}
	
	public static byte[] getAes256Enc(String plainStr) {
		Resource res = new ClassPathResource(AES_KEY);
		try {
			byte[] key = Aes256.getKeyFromFile(res.getFile());
			return Aes256.encrypt(plainStr.getBytes(AES_STR_CHARSET), key);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getAes256EncHexStr(String plainStr) {
		try {
			byte[] enc = getAes256Enc(plainStr);
			return Hex.encodeHexString(enc);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getAes256DecryptStr(String encHexStr) {
		Resource res = new ClassPathResource(AES_KEY);
		try {
			byte[] key = Aes256.getKeyFromFile(res.getFile());
			byte[] dec = Aes256.decrypt(Hex.decodeHex(encHexStr.toCharArray()), key);
			return new String(dec, AES_STR_CHARSET);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static List<String> getAes256DecryptStrList(List<String> encHexStr) {
		List<String> decList = new ArrayList<String>();
		for (String str : encHexStr) {
			str = getAes256DecryptStr(str);
			decList.add(str);
		}
		return decList;
	}
	
	public static byte[] getAes128Enc(String plainStr) {
		Resource res = new ClassPathResource(AES128_KEY);
		try {
			byte[] key = Aes128.getKeyFromFile(res.getFile());
			return Aes128.simpleEncrypt(plainStr.getBytes(AES_STR_CHARSET), key);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getAes128EncHexStr(String plainStr) {
		try {
			byte[] enc = getAes128Enc(plainStr);
			return Hex.encodeHexString(enc);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getAes128DecryptStr(String encHexStr) {
		Resource res = new ClassPathResource(AES128_KEY);
		try {
			byte[] key = Aes128.getKeyFromFile(res.getFile());
			byte[] dec = Aes128.simpleDecrypt(Hex.decodeHex(encHexStr.toCharArray()), key);
			return new String(dec, AES_STR_CHARSET);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getEncAes128WithToken(String plainStr, String token) {
		return getAes128EncHexStr(String.format("%s%s%s", plainStr, DEFAULT_DELIMITER, token));
	}

	public static String getEncAes128WithTimeToken(String plainStr) {
		return getAes128EncHexStr(String.format("%s%s%s", plainStr, DEFAULT_DELIMITER, System.currentTimeMillis()));
	}
	
	public static String getDecAes128WithTimeToken(String encHexStr) {
		return getAes128DecryptStr(encHexStr).split(DEFAULT_DELIMITER)[0];
	}
	
	public static String getEncAes256WithToken(String plainStr, String token) {
		return getAes256EncHexStr(String.format("%s%s%s", plainStr, DEFAULT_DELIMITER, token));
	}

	public static String getEncAes256WithTimeToken(String plainStr) {
		return getAes256EncHexStr(String.format("%s%s%s", plainStr, DEFAULT_DELIMITER, System.currentTimeMillis()));
	}
	
	public static String getDecAes256WithTimeToken(String encHexStr) {
		return getAes256DecryptStr(encHexStr).split(DEFAULT_DELIMITER)[0];
	}
	
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
	
	public static String md5Encrypt(String str){
    	String md5 = "";
    	
    	try{
    		MessageDigest md = MessageDigest.getInstance("MD5"); 
    		md.update(str.getBytes()); 
    		
    		byte byteData[] = md.digest();
    		StringBuffer sb = new StringBuffer();
    		
    		for(int i = 0 ; i < byteData.length ; i++){
    			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
    		}
    		return md5 = sb.toString();
    		
    	}catch(Exception e){
    		md5 = null; 
    		throw new RuntimeException(e);
    	}
    }
	
}
