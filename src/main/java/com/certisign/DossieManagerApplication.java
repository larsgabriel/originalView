package com.certisign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.certisign.dossie.repository.DossieRepository;

@EnableJpaRepositories(basePackageClasses = DossieRepository.class)
@SpringBootApplication(scanBasePackages={
		"com.certisign.dossie.controller", "com.certisign.dossie.model", "com.certisign.dossie.respository", "com.certisign.dossie.service"})
public class DossieManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DossieManagerApplication.class, args);
	}
}
