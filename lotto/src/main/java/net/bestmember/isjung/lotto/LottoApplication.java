package net.bestmember.isjung.lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LottoApplication {

	public static void main(String[] args) {
		Powerball pb = new Powerball();
		pb.main(args);
		SpringApplication.run(LottoApplication.class, args);
	}

}
