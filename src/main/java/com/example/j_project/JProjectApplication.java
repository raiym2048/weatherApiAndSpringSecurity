package com.example.j_project;

import com.example.j_project.models.Product;
import com.example.j_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class JProjectApplication implements CommandLineRunner {
	@Autowired
	ProductRepo ob;

	public static void main(String[] args) {
		SpringApplication.run(JProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(int i = 0;i < 10;i++){
			Random rand = new Random();

			Product product = new Product();
			product.setProductName("p"+i);
			product.setData("data"+i);
			product.setPrize("100"+i+ rand.nextInt());
			ob.save(product);
		};

	}
}
/*
server.port=5952
spring.datasource.url=jdbc:postgresql://${PROD_DB_HOST}:${PROD_DB_PORT}/${PROD_DB_NAME}
spring.datasource.host=${PROD_DB_HOST}
spring.datasource.username=${PROD_DB_USERNAME}
spring.datasource.password=${PROD_DB_PASSWORD}*/

/*
spring.security.oauth2.client.clientId=968740976971-8pr6u7t6hduhrnssk7r6run4al39v3ib.apps.googleusercontent.com
spring.security.oauth2.client.clientSecret=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
spring.security.oauth2.client.accessTokenUri=https://www.googleapis.com/oauth2/v4/token
springsecurity.oauth2.client.userAuthorizationUri=https://accounts.google.com/o/oauth2/v2/auth
spring.security.oauth2.client.clientAuthenticationScheme=form
spring.security.oauth2.client.scope=openid,email,profile
spring.security.oauth2.resource.userInfoUri=https://www.googleapis.com/oauth2/v3/userinfo
spring.security.oauth2.resource.preferTokenInfo=true*/