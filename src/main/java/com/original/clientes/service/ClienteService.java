package com.original.clientes.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.original.clientes.model.Address;
import com.original.clientes.model.Customer;
import com.original.clientes.utils.CustomerUtils;

@Service
public class ClienteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	CustomerUtils customerUtils;
	RestTemplate restTemplate = new RestTemplate();

	public Customer findById(Long id) {
		
		Customer customer = null;
		try {
			String url = "http://localhost:8083/original-api/findById/";
			ResponseEntity<Object> customerObj = restTemplate.postForEntity(url, id, Object.class);
			if (customerObj != null) {
				customer = customerUtils.translateObjet(customerObj.getBody());
			}

		} catch (Exception e) {
			LOGGER.error("Cliente não encontrado!" + e);
		}

		return customer;
	}

	public void addCustomer(Customer customer) {

		try {
			String url = "http://localhost:8083/original-api/addCustomerApi/";
			restTemplate.postForEntity(url, customer, Object.class);
		} catch (Exception e) {
			LOGGER.error("Cliente não encontrado!" + e);
		}
	}

	public void deleteCustomer(Customer customer) {
		try {
			String url = "http://localhost:8083/original-api/deleteCustomer/";
			restTemplate.postForEntity(url, customer, Object.class);
		} catch (Exception e) {
			LOGGER.error("Cliente não encontrado!" + e);
		}
	}

	public List<Customer> findAll() {
		String url = "http://localhost:8083/original-api/findAll/";
		List<Customer> customers = null;
		try {
			ResponseEntity<Object> customersObj = restTemplate.getForEntity(url, Object.class);
			if (customersObj != null) {
				customers = customerUtils.translateObjets((List) customersObj.getBody());
			}
		} catch (Exception e) {
			LOGGER.error("Pedidos não encontrado!" + e);
		}

		return customers;
	}

	public void addAdress(Address address) {
		try {
			String url = "http://localhost:8083/original-api/addAdressApi/";
			restTemplate.postForEntity(url, address, Object.class);
		} catch (Exception e) {
			LOGGER.error("Cliente não encontrado!" + e);
		}
	}


}
