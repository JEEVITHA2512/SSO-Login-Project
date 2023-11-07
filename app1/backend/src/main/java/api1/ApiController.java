package api1;

import java.net.URI;
import java.util.*;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;

import api1.dto.request.OrderRequest;
import api1.dto.response.OrderResponse;
import api1.entity.Order;
import api1.service.OrderService;
import api1.entity.OrderInput;

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
	public OrderResponse addOrder(@RequestBody OrderInput orderInput){
			OrderRequest or = new OrderRequest();
			or.setName(orderInput.name);
			or.setCategory(orderInput.category);
			Date today = new Date();
			or.setDateOfOrder(today);
			or.setPrice(orderInput.price);
			OrderResponse resp = orderService.saveOrder(or);
			return resp;
	}

	@PreAuthorize("hasRole('admin-access')")
	@GetMapping(path="/check/admin")
	public int is_admin(){
		return 1;
	}
	
	@GetMapping(path = "/anon")
	public String anon() {
		return "Hello! This page is accessible to everyone";
	}
}