package com.app.feelog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // quirtz의 멀티스케쥴 기능 허용
public class FeelogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeelogApplication.class, args);
	}

}
