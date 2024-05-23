package com.example.demo.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	@GetMapping("/person/v1")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Harsha Erukala");
	}
	
	@GetMapping("/person/v2")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("erukala","harsha"));
	}
	
	@GetMapping(path = "person", params= "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParam() {
		return new PersonV1("Harsha Erukala");
	}
	@GetMapping(path = "person", params= "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParam() {
		return new PersonV2(new Name("erukala","harsha"));
	}
	
	@GetMapping(path = "person/header", headers= "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeaders() {
		return new PersonV1("Harsha Erukala");
	}
	
	@GetMapping(path = "person/header", headers= "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeaders() {
		return new PersonV2(new Name("erukala","harsha"));
	}
	
	@GetMapping(path = "person/accept",produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeaders() {
		return new PersonV1("Harsha Erukala");
	}
	
	@GetMapping(path = "person/accept1", produces= "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeaders() {
		return new PersonV2(new Name("erukala","harsha"));
	}

}
