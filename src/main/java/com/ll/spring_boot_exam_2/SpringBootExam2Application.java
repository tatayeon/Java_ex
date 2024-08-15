package com.ll.spring_boot_exam_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootExam2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExam2Application.class, args);
	}

}
