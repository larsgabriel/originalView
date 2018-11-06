package com.certisign.dossie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.certisign.dossie.config.property.IntegracaoApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(IntegracaoApiProperty.class)
public class DossieManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DossieManagerApplication.class, args);
	}
}
