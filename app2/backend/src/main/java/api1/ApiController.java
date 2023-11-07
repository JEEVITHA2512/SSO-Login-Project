package api1;

import java.util.*;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;


import api1.dto.request.UserRequest;
import api1.dto.response.UserResponse;
import api1.entity.User;
import api1.service.UserService;
import api1.entity.UserInput;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.lang.Exception;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.lang.Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import api1.entity.UserName;

@RestController
public class ApiController {
	
	private final UserService userService;
	private KeycloakRestTemplate restTemplate;
	private String keycloakServerUrl;

	public ApiController(UserService userService){
		this.userService = userService;
	}

	@PostMapping(path = "/get/user")
	public String getUserDetails(@RequestBody UserName username) throws Exception{
		String keycloak_host = System.getenv("KEYCLOAK_AUTH_SERVER_URL");
		String url = keycloak_host+"/realms/sso-login/protocol/openid-connect/token";
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setDoOutput(true);

		String params = "username=admin&password=admin&client_id=admin-cli&grant_type=password";
		try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.write(params.getBytes(StandardCharsets.UTF_8));
            }
		catch (Exception e) {
            e.printStackTrace();
        }
		connection.connect();
		int responseCode = connection.getResponseCode();
		System.out.println("Response Code: " + responseCode);
		String access_token="";

		if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response data
                // System.out.println("Response Data: " + response.toString());
                // System.out.println("Response Data: " + response);
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, String> jsonMap = objectMapper.readValue(response.toString(), new TypeReference<Map<String, String>>() {});
				access_token = (String) jsonMap.get("access_token");
				System.out.println(jsonMap);


            } else {
                System.out.println("HTTP POST request failed.");
            }

            // Disconnect the connection
            connection.disconnect();

		url = keycloak_host+"/admin/realms/sso-login/users";
		params = "?username="+username.username;

		connection = (HttpURLConnection) new URL(url+params).openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("Authorization", "Bearer "+access_token);
		connection.setDoOutput(true);
		System.out.println("Access Toekn: "+ access_token);

		System.out.println("params: "+params);
		connection.connect();
		responseCode = connection.getResponseCode();
		System.out.println("Response Code: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response data
                System.out.println("Response Data: " + response.toString());
				return response.toString();

	
            } else {
                System.out.println("HTTP POST request failed.");
				return "Error";
            }
		
		
	}

	@PreAuthorize("hasRole('admin-access')")
	@GetMapping(path = "/check/admin")
	public int mod() {
        return 1;
	}
	
	@GetMapping(path = "/anon")
	public String anon() {
		return "Hello! This page is accessible to everyone";
	}
}