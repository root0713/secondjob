package net.bestmember.isjay.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 파라미터 처리 유틸리티
 * 
 * @since 2018. 5. 8.
 * @author isjung
 */
public class ParamUtils {

	/**
	 * map to querystring for http get
	 * 
	 * @since 2018. 5. 8.
	 * @author isjung
	 * @param
	 * @return
	 */
	public static String mapToQuerystring(Map<String, String> queryStringMap) {
		StringBuilder queryString = new StringBuilder();
		try {
			Iterator<String> iter = queryStringMap.keySet().iterator();
			while (iter.hasNext()) {
				String key = StringUtils.getString(iter.next());
				String value = StringUtils.getString(queryStringMap.get(key));
				queryString.append("&").append(URLEncoder.encode(key, "UTF-8")).append("=").append(URLEncoder.encode(value, "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return queryString.toString().length() == 0 ? "" : queryString.toString().substring(1);
	}

	public static String mapToQuerystringArray(Map<String, String[]> queryStringMap) {
		StringBuilder queryString = new StringBuilder();
		Iterator<String> iter = queryStringMap.keySet().iterator();

		while (iter.hasNext()) {
			String key = StringUtils.getString(iter.next());
			String[] values = (String[]) queryStringMap.get(key);
			try {
				queryString.append(URLEncoder.encode(key, "UTF-8")).append("=").append(URLEncoder.encode(StringUtils.getString(Arrays.toString(values)), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return queryString.toString().length() == 0 ? "" : queryString.toString().substring(1);
	}

	/**
	 * rebuild map for param map
	 * 
	 * @since 2018. 5. 8.
	 * @author isjung
	 * @param
	 * @return
	 */
	public static Map<String, String> rebuildParamMap(Map<String, String> paramMap, String[] keys) {
		Map<String, String> resultMap = new HashMap<String, String>();

		for (String key : paramMap.keySet()) {
			InnerLoop: for (String resultKey : keys) {
				if (key.equals(resultKey)) {
					resultMap.put(resultKey, paramMap.get(resultKey));
					break InnerLoop;
				}
			}
		}
		return resultMap;
	}
}
