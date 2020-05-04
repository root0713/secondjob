package net.bestmember.isjay.sinsang.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	@Scheduled(cron = "0 0 1 * * *")
   public void cronJobSch() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      Date now = new Date();
      String strDate = sdf.format(now);
      System.out.println("Java cron job expression:: " + strDate);
   }
   
	// 1초에 한번 실행된다.
	// @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
	@Scheduled(fixedDelay = 1000)
	public void scheduleFixedDelayTask() {
	    System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
	}
	
//	@Scheduled(fixedRate = 1000)
//	public void scheduleFixedRateTask() {
//	  // 주기적으로 실행될 것이다
//	}
}