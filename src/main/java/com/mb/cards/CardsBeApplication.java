package com.mb.cards;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mb.cards"})
@Slf4j
public class CardsBeApplication {

	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext applicationContext = SpringApplication.run(CardsBeApplication.class, args);
			applicationContext.start();
		}catch (Exception e) {
			log.error("Startup error:: {}", e);
		}

	}

}
