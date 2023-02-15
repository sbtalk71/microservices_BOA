package com.demo.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.demo.spring.config.AppConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(AppConfiguration.class)
public class SpringVaultAppApplication implements CommandLineRunner {

	AppConfiguration configuration;

	public SpringVaultAppApplication(AppConfiguration configuration) {
		this.configuration = configuration;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringVaultAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("User is "+configuration.getUsername());

		System.out.println("Password is "+configuration.getPassword());
	}

}
