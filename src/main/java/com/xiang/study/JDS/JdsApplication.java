package com.xiang.study.JDS;

import com.xiang.study.JDS.config.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class JdsApplication {

	public static void main(String[] args) {

		 SpringApplication.run(JdsApplication.class, args);

	}
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CorsFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}

