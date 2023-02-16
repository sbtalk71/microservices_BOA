package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
//@EnableEurekaClient
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
