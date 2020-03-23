package com.mlog.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
* <pre>
* com.mlog.security.CustomUserDetailsService.java
* </pre>
* 
* @desc 		: 사용자 잠금여부 체크를 위해 CUSTOM
* @author  	: isjung
* @since    	: 2017. 5. 31. 오후 4:00:22
*/
public class CustomUserDetailsService extends JdbcDaoImpl {

	@Override
	public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
		super.setUsersByUsernameQuery(usersByUsernameQueryString);
	}

	@Override
	public void setAuthoritiesByUsernameQuery(String queryString) {
		super.setAuthoritiesByUsernameQuery(queryString);
	}

	@Override
	public List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(super.getUsersByUsernameQuery(), new String[] { username },
				new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						String username = rs.getString("USER_ID");
						String userLoginName = rs.getString("USER_NAME");
						String password = rs.getString("PASSWD");
						//int passwdChangeDay = rs.getInt("PASSWD_CHANGE_DAY");
						boolean enabled = rs.getBoolean("ENABLED");
						boolean accountNonExpired = true;
						boolean credentialsNonExpired = true;
						boolean accountNonLocked = !"Y".equals(rs.getString("IS_ACCOUNT_LOCK"));

//						return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, AuthorityUtils.NO_AUTHORITIES);
						return new CustomUser(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, AuthorityUtils.NO_AUTHORITIES, userLoginName);
					}

				});
	}

	@Override
	public UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();

		if (super.isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}
		
		CustomUser customUser =(CustomUser) userFromUserQuery;

		return new CustomUser(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(),
				userFromUserQuery.isAccountNonExpired(), userFromUserQuery.isCredentialsNonExpired(),
				userFromUserQuery.isAccountNonLocked(), combinedAuthorities, customUser.getUserLoginName());
	}

}