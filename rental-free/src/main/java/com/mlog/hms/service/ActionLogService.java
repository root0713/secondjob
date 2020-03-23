package com.mlog.hms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * com.mlog.hms.hybrid.service.ActionLogService.java
 * </pre>
 *
 * @desc	: 액션로그 Service
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
public interface ActionLogService {
	List<HashMap<String, Object>> list(HashMap<String, Object> paramMap);

	Map<String, Object> detail(HashMap<String, Object> paramMap);
	
	int delete();
}
