package api1;

import java.net.URI;
import java.util.*;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

import api1.dto.request.OrderRequest;
import api1.dto.response.OrderResponse;
import api1.entity.Order;
import api1.service.OrderService;

@RestController
public class ApiController {
	
	private final OrderService orderService;

	public ApiController(OrderService orderService){
		this.orderService = orderService;
	}

	@GetMapping(path="api/orders")
    public List<Order> findAllOrder() {
        return orderService.findAllOrder();
    }

	@PostMapping(path="api/add/order")
	public String addOrder(String name, String category, Long price){
		try{
			OrderRequest or = new OrderRequest();
			or.setName(name);
			or.setCategory(category);
			Date today = new Date();
			or.setDateOfOrder(today);
			or.setPrice(price);
			OrderResponse resp = orderService.saveOrder(or);
			return "Success";
		}
		catch (Exception e){
			e.printStackTrace();
			return "Error";
		}
	}

	@PreAuthorize("hasRole('read-access-1')")
	@GetMapping(path = "/check/read")
	public String mod() {
        return "You have read privilages";
	}
	
	@PreAuthorize("hasRole('write-access-1')")
	@GetMapping(path = "/check/write")
	public String users() {
	    return  "You have write privilages";
	}
	
	@GetMapping(path = "/anon")
	public String anon() {
		return "Hello! This page is accessible to everyone";
	}
}