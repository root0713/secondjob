
package com.mlog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mlog.comm.property.AdminProperties;
import lguplus.security.vulner.FilterServlet;

/**
 * <pre>
 * com.mlog.filter.CustomVulnerFilter.java
 * </pre>
 * 
 * @desc : comment at...
 * @author : isjung
 * @since : 2017. 7. 11. 오후 11:07:17
 */
public class CustomVulnerFilter extends FilterServlet {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request ;
		if(excludeUrl(req)){
			 chain.doFilter(request, response);
		}else{
			super.doFilter(request, response, chain);
		}
	}

	private boolean excludeUrl(HttpServletRequest request) {
		String uri = request.getRequestURI().toString().trim();
		boolean result = false;
		String[] excludeEndUrls = AdminProperties.getProperty("excludeEndUrls").split(",");
		for(String excludeEndUrl : excludeEndUrls) {
			if(uri.endsWith(excludeEndUrl)) {
				result = true;
				break;
			}
		}
		return result;
	}

}
