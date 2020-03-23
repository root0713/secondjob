package com.mlog.hms.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * com.mlog.hms.hybrid.dao.ErrorLogDao.java
 * </pre>
 *
 * @desc	: 에러로그 DAO
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
@Repository
public interface ErrorLogDao {

	List<HashMap<String, Object>> list(HashMap<String, Object> paramMap);

	int totalCnt();
	
	int listByTotalCnt(HashMap<String, Object> paramMap);

	HashMap<String, Object> detail(HashMap<String, Object> paramMap);

	int delete(HashMap<String, Object> service);
	
}