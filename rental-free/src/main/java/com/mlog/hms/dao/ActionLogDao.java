package com.mlog.hms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * com.mlog.hms.hybrid.dao.ActionLogDao.java
 * </pre>
 *
 * @desc	: 액션 로그 DAO
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
@Repository
public interface ActionLogDao {
	List<HashMap<String, Object>> list(HashMap<String, Object> paramMap);

	HashMap<String, Object> detail(HashMap<String, Object> paramMap);

	int delete(HashMap<String, Object> paramMap);
}
