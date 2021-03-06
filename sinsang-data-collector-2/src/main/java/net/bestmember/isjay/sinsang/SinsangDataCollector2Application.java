package net.bestmember.isjay.sinsang;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import net.bestmember.isjay.sinsang.mapper.ProductImageMapper;
import net.bestmember.isjay.sinsang.mapper.ProductMapper;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "net.bestmember.isjay.sinsang.mapper")
public class SinsangDataCollector2Application {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	public static void main(String[] args) {
		SpringApplication.run(SinsangDataCollector2Application.class, args);
	}


}
