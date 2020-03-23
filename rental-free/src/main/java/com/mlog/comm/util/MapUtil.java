/**
 * 
 */
package com.mlog.comm.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
* <pre>
* com.mlog.comm.util.MapUtil.java
* </pre>
* 
* @desc 		: MAP UTILITY
* @author  	: isjung
* @since    	: 2016. 11. 21. 오후 12:53:58
*/
public class MapUtil {

	/**
	* <pre>
	* com.mlog.comm.util.MapUtil.orderByKey
	* </pre>
	* 
	* @desc 		: 정렬(오름차순)
	* @author  	: isjung
	* @since    	: 2016. 11. 21. 오후 1:08:28
	* @param paramMap
	* @return
	*/
	public static  HashMap<String, Object> orderByKey(HashMap<String, Object> paramMap){
		return orderByKey(paramMap, true);
	}
	/**
	* <pre>
	* com.mlog.comm.util.MapUtil.orderByKey
	* </pre>
	* 
	* @desc 		: 정렬
	* @author  	: isjung
	* @since    	: 2016. 11. 21. 오후 1:04:50
	* @param paramMap
	* @param isAccending
	* @return
	*/
	public static  HashMap<String, Object> orderByKey(HashMap<String, Object> paramMap, boolean isAccending){
		LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
		TreeMap<String, Object> tm = new TreeMap<String, Object>(paramMap);
		Iterator<String> iteratorKey = isAccending ? tm.keySet().iterator() : tm.descendingKeySet().iterator();
		while (iteratorKey.hasNext()) {
			String key = iteratorKey.next();
			result.put(key, paramMap.get(key));
		}
		return result;
	}

	public static  HashMap<String, Object> orderByKey(HashMap<String, Object> paramMap, boolean isAccending, String[] priorityKeys){
		LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
		TreeMap<String, Object> tm = new TreeMap<String, Object>(paramMap);
		for(String key : priorityKeys) {
			tm.remove(key);
			result.put(key, paramMap.get(key));
		}
		Iterator<String> iteratorKey = isAccending ? tm.keySet().iterator() : tm.descendingKeySet().iterator();
		while (iteratorKey.hasNext()) {
			String key = iteratorKey.next();
			result.put(key, paramMap.get(key));
		}
		return result;
	}
}
