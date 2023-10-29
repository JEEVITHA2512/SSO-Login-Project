package api1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	

	@PreAuthorize("hasRole('admin-access-2')")
	@GetMapping(path = "/check/admin")
	public String mod() {
        return "You have admin privilages";
	}
	
	@PreAuthorize("hasRole('customer-access-2')")
	@GetMapping(path = "/check/customer")
	public String users() {
	    return  "You have customer privilages";
	}
	
	@GetMapping(path = "/anon")
	public String anon() {
		return "Hello! This page is accessible to everyone";
	}
}