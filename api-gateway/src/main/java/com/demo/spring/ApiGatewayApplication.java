package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@GetMapping(path = "/hrServiceFallback")
	public ResponseEntity<String> fallbasckForHrService() {
		return ResponseEntity.ok("HR Service Unavailable");
	}

	@Bean
	public RouteLocator appRoutes(RouteLocatorBuilder routeBuilder) {

		return routeBuilder
				.routes()
				.route(p -> p.path("/hr/**").uri("http://localhost:9088"))
				
						.route(p -> p.path("/emp/**").uri("lb://emp-service"))
				.route(p -> p.path("/dept/**").uri("lb://dept-service"))
				.build();
	}
}

//filters(f -> f.circuitBreaker(c -> c.name("myCircuitBreaker").fallbackUri("forward://hrServiceFallback"))