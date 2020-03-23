package com.mlog.hms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * com.mlog.hms.hybrid.dao.CodeDao.java
 * </pre>
 *
 * @desc	: 코드 관리 DAO
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
@Repository
public interface CodeDao {

	List<HashMap<String, Object>> list(HashMap<String, Object> paramMap);

	int totalCnt();
	
	int listByTotalCnt(HashMap<String, Object> paramMap);

	Map<String, Object> detail(HashMap<String, Object> paramMap);

	int updateCode(HashMap<String, Object> paramMap);

	int insertCode(HashMap<String, Object> paramMap);

	int delete(HashMap<String, Object> paramMap);

	List<HashMap<String, Object>> codeGrpList(HashMap<String, Object> paramMap);
	
	int codeGrpListByTotalCnt(HashMap<String, Object> paramMap);
	
	List<HashMap<String, Object>> codeGrpSelctList();

	Map<String, Object> codeGroupDetail(HashMap<String, Object> paramMap);

	int updateCodeGrp(HashMap<String, Object> paramMap);
	
	int insertCodeGrp(HashMap<String, Object> paramMap);

	int deleteCodeGrp(HashMap<String, Object> paramMap);
	
	List<HashMap<String, Object>> listByCodeGrp(HashMap<String, Object> paramMap);
	
}
