package com.original.clientes.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.original.clientes.model.Customer;

@Component
public class CustomerUtils {

	public List<Customer> translateObjets(List object) throws JsonParseException, JsonMappingException, IOException {

		List<Customer> dtos = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		for (Object o : object) {
			@SuppressWarnings("unchecked")
			String jsonString = new JSONObject((Map<Object, Customer>) o).toString();

			Customer vo = objectMapper.readValue(jsonString, Customer.class);
			dtos.add(vo);

		}

		return dtos;
	}
	
	public Customer translateObjet(Object object) throws JsonParseException, JsonMappingException, IOException {

		Customer customer = new Customer();
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		String jsonString = new JSONObject((Map<Object, Customer>) object).toString();
		customer = objectMapper.readValue(jsonString, Customer.class);
		return customer;
	}
}
