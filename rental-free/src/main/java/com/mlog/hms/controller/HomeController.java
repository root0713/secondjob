package com.mlog.hms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping(value="/")
public class HomeController {

	@ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value ={"/"})
    public ModelAndView listByWebui(Model model) {
		return new ModelAndView("redirect:/error/webui/list");
    }
}
