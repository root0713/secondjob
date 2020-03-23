/**
 * 
 */
package com.mlog.security;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * com.mlog.comm.util.ShaEncoder.java
 * </pre>
 * 
 * @desc : 비밀번호 암호화
 * @author : isjung
 * @since : 2017. 5. 26. 오후 3:23:52
 */

@Service("shaEncoder")
public class ShaEncoder {

	@Resource(name = "shaPasswordEncoder")
	private ShaPasswordEncoder encoder;

	public String encoding(String str) {
		return encoder.encodePassword(str, null);
	}
}