package com.example.RestDemo.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersoningControlling {
	
	@GetMapping(value="/version/v1")
	public PersonV1 versionV1() {
		return new PersonV1("first");
	}
	
	@GetMapping(value="/version/v2")
	public PersonV2 versionV2() {
		return new PersonV2( new Name("first","last"));
	}

	
//	Versoning using the param attribute
	@GetMapping(value="/version/param",params="version=v1")
	public PersonV1 paramV1() {
		return new PersonV1("bala");
	}
	
	@GetMapping(value="/version/param",params="version=v2")
	public PersonV2 paramV2() {
		return new PersonV2( new Name("bala","Krishna"));
	}
}
