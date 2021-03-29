package com.example.demolaunchdarkly;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoLaunchdarklyApplication {

	private Logger logger = LoggerFactory.getLogger(DemoLaunchdarklyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoLaunchdarklyApplication.class, args);
	}

	@PreDestroy
	private void closingLDClient() throws IOException {
		logger.info("On preDestroy calling LD client close");
		FeatureMain.getLdClient().close();
	}

}
