package io.github.cepr0.dualdb.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Cepr0, 2017-12-22
 */
@EnableAsync
@SpringBootApplication(scanBasePackages = "io.github.cepr0.dualdb")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
