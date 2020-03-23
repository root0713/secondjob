package com.mlog.comm.listener;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mlog.comm.constant.CodeConstant;
import com.mlog.comm.util.CodeUtil;
import com.mlog.hms.service.CodeService;

@Component
public class HmsApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired private CodeService	codeService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("grpCd", CodeConstant.SERVICE_CODE);
		CodeUtil.serviceList = codeService.listByCodeGrp(paramMap);
	}
}
