package com.mlog.hms.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * com.mlog.hms.hybrid.dao.UserDao.java
 * </pre>
 *
 * @desc	: 사용자 관리 DAO
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
@Repository
public interface UserDao {

	List<HashMap<String, Object>> authList(HashMap<String, Object> paramMap);
	
	List<HashMap<String, Object>> list(HashMap<String, Object> paramMap);
	
	int listByTotalCnt(HashMap<String, Object> paramMap);

	int updateUser(HashMap<String, Object> paramMap);

	int totalCnt();

	HashMap<String, Object> detail(HashMap<String, Object> paramMap);

	int updateUserRoles(HashMap<String, Object> paramMap);

	int insertUser(HashMap<String, Object> paramMap);

	void insertUserRoles(HashMap<String, Object> paramMap);

	int deleteUser(HashMap<String, Object> paramMap);

}
