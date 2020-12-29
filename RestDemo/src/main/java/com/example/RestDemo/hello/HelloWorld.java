package com.example.RestDemo.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@GetMapping("/hello")
	public String Hello() {
		return "Hello World";
	}
	
	@GetMapping("/hello-bean")
	public HelloWorldBean HelloWorldBean() {
		return new HelloWorldBean("HelloWorld Bean");
		
	}
	
	@GetMapping("/hello/path-variable/{name}")
	public HelloWorldBean pathVariableBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World , %s", name));
	}
}
