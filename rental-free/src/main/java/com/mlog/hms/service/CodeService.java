package com.mlog.hms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * com.mlog.hms.hybrid.service.CodeService.java
 * </pre>
 *
 * @desc	: 코드 관리 Service
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
public interface CodeService {

	List<HashMap<String, Object>> list(HashMap<String, Object> paramMap);

	int totalCnt(HashMap<String, Object> paramMap);

	Map<String, Object> detail(HashMap<String, Object> paramMap);

	int save(HashMap<String, Object> paramMap);

	int delete(HashMap<String, Object> paramMap);

	List<HashMap<String, Object>> codeGroupList(HashMap<String, Object> paramMap);
	
	int totalCntByCodeGroup(HashMap<String, Object> paramMap);
	
	List<HashMap<String, Object>> codeGroupSelectList();

	Map<String, Object> codeGroupDetail(HashMap<String, Object> paramMap);

	int codeGroupSave(HashMap<String, Object> paramMap);

	int codeGroupDelete(HashMap<String, Object> paramMap);
	
	List<HashMap<String, Object>> listByCodeGrp(HashMap<String, Object> paramMap);

}
