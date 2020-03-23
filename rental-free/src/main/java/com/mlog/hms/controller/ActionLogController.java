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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mlog.comm.util.CodeUtil;
import com.mlog.hms.service.ActionLogService;

/**
 * <pre>
 * com.mlog.hms.admin.controller.ActionLogController.java
 * </pre>
 *
 * @desc	: 사용자 액션 로그 컨트롤러
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
@Controller
@RequestMapping(value="/action")
public class ActionLogController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private ActionLogService actionLogService;
	
	@ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value ={"/user/analysis"})
	 public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("isWeb", "Y");
		model.addAttribute("serviceCodeList", CodeUtil.serviceList);
		return new ModelAndView("/hms/analysis.default");
    }
	
	
	@RequestMapping(value ="/ajax/list", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> ajaxList(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) {
		List<HashMap<String, Object>> resultList = null;
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			String saId = (String) paramMap.get("searchSaId");
			if (saId != null && !"".equals(saId)) {
				resultList = actionLogService.list(paramMap);
			}
		}catch(Exception e){
			logger.info("[/action/ajax/list]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("resultList", resultList);
		return result;
	}
	
	@RequestMapping(value ="/ajax/detail", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> ajaxDetail(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			data = actionLogService.detail(paramMap);
		}catch(Exception e){
			logger.info("[/action/ajax/detail]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("result", data);
		return result;
	}
	
}
