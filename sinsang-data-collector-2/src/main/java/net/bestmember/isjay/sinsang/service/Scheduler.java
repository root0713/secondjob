package net.bestmember.isjay.sinsang.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.bestmember.isjay.common.util.HttpClient;

@Component
public class Scheduler {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	// 초 분 시 일 월 주(년)
	@Scheduled(cron = "0 0 1 * * ?")
	public void cronJobSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		long elapsedTime = System.currentTimeMillis();
		logger.info("crawler job started...  " + strDate);
		HttpClient.httpGet("http://127.0.0.1:8080/collect/women/1000/5000/29999");
		logger.info("crawler job end :: elapsedTime :: " + (System.currentTimeMillis() - elapsedTime) + " ms!!! ");
	}

	// @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
//	@Scheduled(fixedDelay = 60 * 60 * 1000)
//	public void scheduleFixedDelayTask() {
//		System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
//		
//	}

//	@Scheduled(fixedRate = 1000)
//	public void scheduleFixedRateTask() {
//	  // 주기적으로 실행될 것이다
//	}
}