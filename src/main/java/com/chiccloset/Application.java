package com.chiccloset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class Application {

	public HandlerMapping handlerMapping() {
		return new RequestMappingHandlerMapping();
	}

	public HandlerAdapter handlerAdapter() {
		return new RequestMappingHandlerAdapter();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}