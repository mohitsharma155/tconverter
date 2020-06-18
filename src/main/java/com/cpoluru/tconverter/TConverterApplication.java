package com.cpoluru.tconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.cpoluru.tconverter"})
public class TConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TConverterApplication.class, args);
	}

}
