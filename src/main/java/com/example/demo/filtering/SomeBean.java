package com.example.demo.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties({"field1","field2"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	//@JsonIgnore
	private String field2;
	private String field3;

	public SomeBean(String filed1, String filed2, String filed3) {
		super();
		this.field1 = filed1;
		this.field2 = filed2;
		this.field3 = filed3;
	}

	public String getFiled1() {
		return field1;
	}

	public String getFiled2() {
		return field2;
	}

	public String getFiled3() {
		return field3;
	}

	@Override
	public String toString() {
		return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}
	

	
	

}
