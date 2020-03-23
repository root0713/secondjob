package com.mlog.hms.service;

import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 * com.mlog.hms.hybrid.service.ErrorLogService.java
 * </pre>
 *
 * @desc	: 애러로그 Service
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
public interface ErrorLogService {

	List<HashMap<String, Object>> list(HashMap<String, Object> paramMap);

	int totalCnt(HashMap<String, Object> paramMap);

	HashMap<String, Object> detail(HashMap<String, Object> paramMap);

	int delete();

}
