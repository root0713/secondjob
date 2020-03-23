package com.mlog.comm.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mlog.hms.service.ActionLogService;
import com.mlog.hms.service.ErrorLogService;

@Component 
public class LogDeleteJob {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private ErrorLogService errorLogService;
	
	@Autowired private ActionLogService actionLogService;
	
	@Scheduled(cron = "${daily.delete.errorlog.cron}")
    public void ErrorLogDelete() throws Exception {
		logger.info("#### Error Log 삭제 START");
		
		errorLogService.delete();
		
		actionLogService.delete();
		
		logger.info("#### Error Log 삭제 END");	
    }
}
