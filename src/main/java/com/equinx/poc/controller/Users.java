package com.equinx.poc.controller;

import io.apiwiz.compliance.config.EnableCompliance;
import org.bouncycastle.mime.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

@RestController
@EnableCompliance
@RequestMapping("/v1/usermanagement/access/v2/users")
public class Users {
@Autowired
private RestTemplate restTemplate;
@Value("${api.copy.users:null}")
private String copyPerm;
@Value("${api.get.user.details:null}")
private String userDetails;

@GetMapping
public ResponseEntity<Map<String, Object>> getAllUsers(
        @RequestParam(value = "offset", defaultValue = "0") int offset,
        @RequestParam(value = "limit", defaultValue = "25") int limit) {
    
    Map<String, Object> response = new HashMap<>();
    
    List<Map<String, Object>> users = Arrays.asList(
            createSampleUser("john_doe", "John", "Doe", "ACTIVE"),
            createSampleUser("jane_doe", "Jane", "Doe", "INACTIVE")
    );
    
    Map<String, Object> pagination = new HashMap<>();
    pagination.put("offset", offset);
    pagination.put("limit", limit);
    pagination.put("total", users.size());
    pagination.put("next", "/users?offset=" + (offset + limit) + "&limit=" + limit);
    pagination.put("previous", (offset > 0) ? "/users?offset=" + (offset - limit) + "&limit=" + limit : null);
    
    response.put("data", users);
    response.put("pagination", pagination);
    
    return new ResponseEntity<>(response, HttpStatus.OK);
}

// Create a user
@PostMapping
public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, Object> request,
                                                      @RequestHeader(value = "deviate", required = false) boolean deviate,
                                                      @RequestHeader(value = "Authorization",required = false) String authorzation,
                                                      @RequestHeader(value = "enableTracing",required = false) boolean enableTracing) throws URISyntaxException {
    if (enableTracing) {
        Map<String, Object> dataMap = Map.ofEntries(
                Map.entry("sourceRegisteredUser", "orgPermissionUser"),
                Map.entry("targetRegisteredUsers", List.of("jhon"))
        );
        HttpHeaders headers = new HttpHeaders();
        headers.add("enableTracing",String.valueOf(Boolean.TRUE));
        headers.add("deviate",String.valueOf(deviate));
        headers.add("Authorization","12983712");
        restTemplate.exchange(new URI(copyPerm), HttpMethod.POST,new HttpEntity<>(dataMap,headers),Object.class);
    }
    HttpHeaders headers =new HttpHeaders();
    headers.add("Location","/users/jhon");
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
}

// Update user details
@PatchMapping("/{username}")
public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String username, @RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", "User Updated Successfully");
    return new ResponseEntity<>(response, HttpStatus.OK);
}

// Terminate user access
@PostMapping("/accessChange")
public ResponseEntity<Map<String, Object>> terminateUserAccess(@RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", "SUCCESS");
    return new ResponseEntity<>(response, HttpStatus.OK);
}

// Copy user permissions
@PostMapping("/permissionsCopy")
public ResponseEntity<Map<String, Object>> copyUserPermissions(@RequestBody Map<String, Object> request,
                                                               @RequestHeader(value = "deviate", required = false) boolean deviate,
                                                               @RequestHeader(value = "Authorization", required = false) String Authorization,
                                                               @RequestHeader(value = "enableTracing",required = false) boolean enableTracing) throws URISyntaxException {
    if(enableTracing){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","12983712");
        if(deviate){
            return null;
        }
        restTemplate.exchange(new URI(userDetails), HttpMethod.GET,new HttpEntity<>(headers),Object.class);
    }
    Map<String, Object> response = new HashMap<>();
    response.put("successes", Arrays.asList("john_doe"));
    response.put("failures", new HashMap<>());
    return new ResponseEntity<>(response, HttpStatus.OK);
}

private Map<String, Object> createSampleUser(String username, String firstName, String lastName, String status) {
    Map<String, Object> user = new HashMap<>();
    user.put("username", username);
    user.put("firstName", firstName);
    user.put("lastName", lastName);
    user.put("email", username + "@acme.com");
    user.put("status", status);
    // Add other fields as needed
    return user;
}
@GetMapping("/{username}")
public ResponseEntity<Map<String, Object>> getUserByUsername(@PathVariable String username) {
    Map<String, Object> response = createSampleUser(username, "John", "Doe", "ACTIVE");
    return new ResponseEntity<>(response, HttpStatus.OK);
}
}