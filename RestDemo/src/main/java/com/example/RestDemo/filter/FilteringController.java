package com.example.RestDemo.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping(path="/filtering")
	public MappingJacksonValue filtering() {
		
		SomeBean someBean = new SomeBean("field1","field2","field3");
		
		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		
		FilterProvider filter = new SimpleFilterProvider().addFilter("someBean", propertyFilter);
				
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		
		mapping.setFilters(filter);
		
		return mapping;
		
		
	}
	
	@GetMapping(path="/filtering-list")
	public MappingJacksonValue filterList() {
		
		List<SomeBean> list = Arrays.asList(new SomeBean("field1","field2","field3"),
				new SomeBean("field1","field2","field3"));
		
		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filter = new SimpleFilterProvider().addFilter("someBean", propertyFilter);
				
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		
		mapping.setFilters(filter);
		
		return mapping;
		
		
	}
}
