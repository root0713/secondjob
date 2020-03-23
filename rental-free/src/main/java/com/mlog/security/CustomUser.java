/**
 * 
 */
package com.mlog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
* <pre>
* com.mlog.security.CustomUser.java
* </pre>
* 
* @desc 		: comment at...
* @author  	: isjung
* @since    	: 2017. 6. 2. 오전 11:58:17
*/
public class CustomUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String userLoginName) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userLoginName = userLoginName;
	}
	

	private String userLoginName;

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	

}
