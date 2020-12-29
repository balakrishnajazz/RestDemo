package com.example.RestDemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 public static final Contact DEFAULT_CONTACT
     = new Contact(
     "Bala Krishna",
     "None",
     "bala@gmail.com");
	 
//	 name and website and gmail
	 public static final ApiInfo DEFAULT
     = new ApiInfo(
     "my app Api Documentation",
     "my app Api Documentation",
     "1.0",
     "urn:tos",
     DEFAULT_CONTACT,
     "Apache 2.0",
     "http://www.apache.org/licenses/LICENSE-2.0",
     new ArrayList<>());

	 private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>() 
	 {
		 
		 {
			 add("application/json");
			 add("application/xml");
		 }
	 };
	 
//	 if there are any terms of service to use your URL you can specify in urn:tos
//	 Default_contact specifies who is the contact person.
//	 we can specify our own data at that place

	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				;
	}
}
