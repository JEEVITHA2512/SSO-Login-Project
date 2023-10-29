package api1;

import java.net.URI;
import java.util.List;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	

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
		System.out.println(keycloakServerUrl);
		return "Hello! This page is accessible to everyone";
	}
}