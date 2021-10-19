package br.jamtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class JamtechApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(JamtechApplication.class, args);
	}
	

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

}
