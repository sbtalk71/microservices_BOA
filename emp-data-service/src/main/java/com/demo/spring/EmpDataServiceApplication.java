package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@EnableEurekaClient
public class EmpDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpDataServiceApplication.class, args);
	}


	//@Bean
	//@ConfigurationProperties(prefix = "app.datasource")
	//@Profile("testing")
	public HikariDataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
}
