package com.mlog.security;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
* <pre>
* com.mlog.security.LoginController.java
* </pre>
* 
* @desc          : 로그인
* @author        : Sujin
* @since          : 2016. 11. 18. 오후 6:52:19
*/
@Controller
public class LoginController {
	
	
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(@RequestParam(value = "error", required = false) String error, HttpServletRequest request, Model model, Principal principal) {
		if (error != null) {
			model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		} 
				String viewName = "/security/login/login.empty";
		
		if (principal != null) {
			viewName = "redirect:/error/webui/list";
		}
		
		return new ModelAndView(viewName);
	}
	
	@RequestMapping(value = "/login/success", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView loginSuccess(Authentication auth, HttpSession session) {
			CustomUser customUser = (CustomUser) auth.getPrincipal();
			session.setAttribute("customUser", customUser);
			return new ModelAndView("redirect:/");
	}
		
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView logout(HttpServletRequest request, Model model) {
		new SecurityContextLogoutHandler().logout(request, null, null);
		model.addAttribute("logout", "정상적으로 로그아웃 되었습니다.");
		return new ModelAndView("/security/login/login.empty");
	}
	
	/**
	* <pre>
	* com.mlog.security.LoginController.getErrorMessage
	* </pre>
	* 
	* @desc 		: 로그인 오류 메세지 처리 
	* @author  	: isjung
	* @since    	: 2017. 5. 31. 오후 4:02:00
	* @param request
	* @param key
	* @return
	*/
	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		return exception instanceof LockedException ? "패스워드를 5회 이상 잘 못 입력하여 계정이 잠겼습니다.<br>관리자에게 연락 바랍니다." : "인증이 실패하였습니다.";
	}
	
}