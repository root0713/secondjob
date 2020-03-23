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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mlog.hms.service.CodeService;
import com.mlog.security.CustomUser;


/**
 * <pre>
 * com.mlog.hms.admin.controller.CodeController.java
 * </pre>
 *
 * @desc	: 코드 관리
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
@Controller
@RequestMapping("/code")
public class CodeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private CodeService codeService;
	
	@ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value ={"/list"})
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<HashMap<String, Object>> codeGrpList = codeService.codeGroupSelectList();
		model.addAttribute("codeGrpList", codeGrpList);
		return new ModelAndView("/hms/code.default");
    }
	

	@ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value ={"/group/list"})
    public ModelAndView codeGroupList(HttpServletRequest request, HttpServletResponse response, Model model) {
		return new ModelAndView("/hms/code_group.default");
    }
	
	@RequestMapping(value ="/ajax/list", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> ajaxList(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) {
		int total = 0;
		List<HashMap<String, Object>> resultList = null;
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			resultList = codeService.list(paramMap);
			if(resultList.size() > 0){
				total = codeService.totalCnt(paramMap);
			}
		}catch(Exception e){
			logger.info("[/code/group/list]["+e.getClass().getName()+"]["+e.getMessage()+"]");
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
			data = codeService.detail(paramMap);
		}catch(Exception e){
			logger.info("[/code/ajax/detail]["+e.getClass().getName()+"]["+e.getMessage()+"]");
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
			data.put("saveCnt", codeService.save(paramMap));
		}catch(Exception e){
			logger.info("[/code/ajax/update]["+e.getClass().getName()+"]["+e.getMessage()+"]");
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
			data.put("deleteCnt", codeService.delete(paramMap));
		}catch(Exception e){
			logger.info("[/code/ajax/delete]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("result", data);
		return result;
	}
	
	@RequestMapping(value ="/group/ajax/list", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> codeGroupAjaxList(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) {
		int total = 0;
		List<HashMap<String, Object>> resultList = null;
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			resultList = codeService.codeGroupList(paramMap);
			if(resultList.size() > 0){
				total = codeService.totalCntByCodeGroup(paramMap);
			}
		}catch(Exception e){
			logger.info("[/code/group/ajax/list]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("resultList", resultList);
		result.put("totalCnt", total);
		return result;
	}
	
	@RequestMapping(value ="/group/ajax/detail", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> codeGroupAjaxDetail(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) {
		Map<String, Object> data = null;
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			data = codeService.codeGroupDetail(paramMap);
		}catch(Exception e){
			logger.info("[/code/group/ajax/detail]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("result", data);
		return result;
	}

	@RequestMapping(value ="/group/ajax/save", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> codeGroupAjaxUpdate(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request, Authentication auth) {
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
			data.put("saveCnt", codeService.codeGroupSave(paramMap));
		}catch(Exception e){
			logger.info("[/code/group/ajax/update]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("result", data);
		return result;
	}
	

	@RequestMapping(value ="/group/ajax/delete", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> codeGroupAjaxDelete(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request, Authentication auth) {
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
			data.put("deleteCnt", codeService.codeGroupDelete(paramMap));
		}catch(Exception e){
			logger.info("[/code/group/ajax/delete]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("result", data);
		return result;
	}
}
