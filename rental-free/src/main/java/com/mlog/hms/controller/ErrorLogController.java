package com.mlog.hms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mlog.comm.service.MimsService;
import com.mlog.comm.util.CodeUtil;
import com.mlog.hms.service.ErrorLogService;

/**
 * <pre>
 * com.mlog.hms.admin.controller.ErrorLogController.java
 * </pre>
 *
 * @desc	: 에러로그 관리
 * @author	: kimmyeongsu
 * @since	: Aug 5, 2019
 */
@Controller
@RequestMapping(value="/error")
public class ErrorLogController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private ErrorLogService errorLogService;
	@Autowired private MimsService mimsService;
	
	@ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value ={"/webui/list"})
    public ModelAndView listByWebui(Model model) {
		model.addAttribute("isWeb", "Y");
		model.addAttribute("serviceCodeList", CodeUtil.serviceList);
		model.addAttribute("vipsList", mimsService.getVipsList());
		return new ModelAndView("/hms/error.default");
    }
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value ={"/native/list"})
	public ModelAndView listByNative(Model model) {
		model.addAttribute("isWeb", "N");
		model.addAttribute("serviceCodeList", CodeUtil.serviceList);
		return new ModelAndView("/hms/error.default");
	}
	
	@RequestMapping(value ="/ajax/list", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> ajaxList(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) {
		int total = 0;
		List<HashMap<String, Object>> resultList = null;
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			resultList = errorLogService.list(paramMap);
			if(resultList.size() > 0){
				total = errorLogService.totalCnt(paramMap);
			}
		}catch(Exception e){
			logger.info("[/error/ajax/list]["+e.getClass().getName()+"]["+e.getMessage()+"]");
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
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			data = errorLogService.detail(paramMap);
		}catch(Exception e){
			logger.info("[/error/ajax/detail]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("params", paramMap);
		result.put("result", data);
		return result;
	}
	
	@RequestMapping(value ="/ajax/vipsList", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> ajaxVipsList() {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			data.put("HMS_MONITORING", mimsService.getVipsList());
		}catch(Exception e){
			logger.info("[/error/ajax/vipsList]["+e.getClass().getName()+"]["+e.getMessage()+"]");
			e.printStackTrace();
		}
		
		result.put("result", data);
		return result;
	}
}
