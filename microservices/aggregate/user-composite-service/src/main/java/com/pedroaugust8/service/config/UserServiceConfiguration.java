package com.pedroaugust8.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pedroaugust8.service.gateway.service.UserService;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
public class UserServiceConfiguration {
	
	@Value("${UserService}")
	private String path;
	
	@Bean
	public UserService getUserService() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(UserService.class, path);
	}
}