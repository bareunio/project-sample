package io.bareun.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"io.bareun"})
@SpringBootApplication
public class ProjectSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSampleApplication.class, args);
	}

}
