package my.spring.app.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	private static final Logger logger = LogManager.getLogger(TestApplication.class);
	public static void main(String[] args) {
		logger.info("Start app");
		SpringApplication.run(TestApplication.class, args);
	}

}
