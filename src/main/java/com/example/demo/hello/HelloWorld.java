package com.example.demo.hello;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.hellobean.HelloWorldBean;

@RestController
public class HelloWorld {
	private MessageSource messageSource;
	
	public HelloWorld(MessageSource messageSource) {
		this.messageSource= messageSource;
	}
	
	@GetMapping(path = "/hi")
	 public String helloWorld() {
		 return "hello";
	 }
	
	
	@GetMapping(path = "/hello-world-bean")
	 public  HelloWorldBean helloWorldBean() {
		 return new HelloWorldBean("hello");
	 }
	
	//pathVariables we need to match variables like id,name
	@GetMapping(path = "/hello-world/pathvariable/{name}")
	 public  HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		 return new HelloWorldBean(String.format("hello , %s", name));
	 }
	
	
	//get: Retrieve details of a resource
	//post: create a new resource
	//put: update an existing resource
	//patch-update part of a resource
	//delete- delete a resource
	
	@GetMapping(path = "/hi-internationalized")
	 public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default message", locale);
		 //return "hello";
	 }
	//good.morning.message
	//English - en (good morning)
	//dutch - n1 (goedomorgen)

}
