package com.mlog.hms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * com.mlog.hms.hybrid.service.UserService.java
 * </pre>
 *
 * @desc	: 사용자 관리 Service
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
public interface UserService {

	List<HashMap<String, Object>> list(HashMap<String, Object> paramMap);

	int totalCnt(HashMap<String, Object> paramMap);

	HashMap<String, Object> detail(HashMap<String, Object> paramMap);

	Object save(HashMap<String, Object> paramMap);

	int delete(HashMap<String, Object> paramMap);

}
