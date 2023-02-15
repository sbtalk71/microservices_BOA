package com.demo.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("example")

public class AppConfiguration {
	private String username;
	private String password;

	public AppConfiguration() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
