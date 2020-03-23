package com.mlog.hms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mlog.hms.service.UserService;
import com.mlog.security.CustomUser;


/**
 * <pre>
 * com.mlog.hms.hybrid.controller.UserController.java
 * </pre>
 *
 * @desc	: 사용자 관리
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private UserService userService;
	
	@ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value ={"/list"})
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Model model) {
		return new ModelAndView("/hms/user.default");
    }
	
	@RequestMapping(value ="/ajax/list", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> ajaxList(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) {
		int total = 0;
		List<HashMap<String, Object>> resultList = null;
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			resultList = userService.list(paramMap);
			if(resultList.size() > 0){
				total = userService.totalCnt(paramMap);
			}
		}catch(Exception e){
			logger.info("[/user/ajax/list]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("resultList", resultList);
		result.put("totalCnt", total);
		return result;
	}
	
	@RequestMapping(value ="/ajax/detail", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> ajaxDetail(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) {
		Map<String, Object> data = null;
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			data = userService.detail(paramMap);
		}catch(Exception e){
			logger.info("[/user/ajax/detail]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("result", data);
		return result;
	}

	@RequestMapping(value ="/ajax/save", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> ajaxUpdate(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request, Authentication auth) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		CustomUser customUser = (CustomUser) auth.getPrincipal();
		String authorities = customUser.getAuthorities().toString();		
		
		if(authorities.equals("[ROLE_ADMIN]") || authorities.equals("[ROLE_SUPER_ADMIN]")){
			paramMap.put("isAdmin", "true");
		} else {
			paramMap.put("isAdmin", "false");
		}
		
		paramMap.put("loginId", customUser.getUsername());
		try {
			data.put("saveCnt", userService.save(paramMap));
		}catch(Exception e){
			logger.info("[/user/ajax/update]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("result", data);
		return result;
	}
	

	@RequestMapping(value ="/ajax/delete", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> ajaxDelete(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request, Authentication auth) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		CustomUser customUser = (CustomUser) auth.getPrincipal();
		String authorities = customUser.getAuthorities().toString();		
		
		if(authorities.equals("[ROLE_ADMIN]") || authorities.equals("[ROLE_SUPER_ADMIN]")){
			paramMap.put("isAdmin", "true");
		} else {
			paramMap.put("isAdmin", "false");
		}
		
		paramMap.put("loginId", customUser.getUsername());
		try {
			data.put("deleteCnt", userService.delete(paramMap));
		}catch(Exception e){
			logger.info("[/user/ajax/delete]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("result", data);
		return result;
	}
	
}
