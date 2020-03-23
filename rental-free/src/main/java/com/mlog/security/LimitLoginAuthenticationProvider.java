package com.mlog.security;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.mlog.hms.dao.UserDao;

/**
* <pre>
* com.mlog.security.LimitLoginAuthenticationProvider.java
* </pre>
* 
* @desc 		: 사용자 패스워드 제한을 위해 CUSTOM
* @author  	: isjung
* @since    	: 2017. 5. 31. 오후 3:59:17
*/
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired
	UserDao userDao;
	
	@Value("${passwd.restrict}")
	private int passwdRestrictCount;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", authentication.getName());
		List<HashMap<String, Object>> userlist = userDao.authList(paramMap);
		HashMap<String, Object> user = null; 
		try {
			Authentication auth = super.authenticate(authentication);
			paramMap.put("loginFailCount", "0");
			userDao.updateUser(paramMap);
			return auth;

		} catch (BadCredentialsException e) {
			if (userlist.size() > 0) {
				user = userlist.get(0);
				int lastAttempts = Integer.valueOf(user.get("LOGIN_FAIL_COUNT").toString()) ;
				if(lastAttempts > passwdRestrictCount-2){
					paramMap.put("isAccountLock", "Y");
				}
				paramMap.put("loginFailCount", (lastAttempts + 1) + "");
				userDao.updateUser(paramMap);
			}
			throw e;
		} catch (LockedException e) {
			throw new LockedException("");
		}
	}
}