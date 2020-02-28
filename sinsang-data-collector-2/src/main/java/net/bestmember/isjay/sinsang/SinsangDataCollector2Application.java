package net.bestmember.isjay.sinsang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.bestmember.isjay.sinsang.mapper.ProductImageMapper;
import net.bestmember.isjay.sinsang.mapper.ProductMapper;

@SpringBootApplication
public class SinsangDataCollector2Application implements CommandLineRunner{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	public static void main(String[] args) {
		SpringApplication.run(SinsangDataCollector2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		logger.info("run started!!!!!!!");
//		System.out.println(productMapper.findAll().toString());
//		logger.info("run end!!!!!!!");
	}

}
