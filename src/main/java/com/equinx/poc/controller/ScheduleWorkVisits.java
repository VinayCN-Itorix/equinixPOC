package com.equinx.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/schedulingworkvisits/colocations/v2/orders/workVisits")
public class ScheduleWorkVisits {

@Autowired
private RestTemplate restTemplate;

@Value("${api.update.notes:null}")
private String updateNotes;
@PostMapping
public ResponseEntity<Map<String, Object>> createWorkVisit(@RequestBody Map<String, Object> request,
                                                           @RequestHeader(value = "deviate", required = false) boolean deviate,
                                                           @RequestHeader(value = "Authorization", required = false) String Authorization,
                                                           @RequestHeader(value = "enableTracing",required = false) boolean enableTracing) throws URISyntaxException {
   
    Map<String, Object> response = Map.of(
            "location", "/orders/1-23232322"
    );
    if(enableTracing){
        HttpHeaders headers = new HttpHeaders();
        headers.add("enableTracing",String.valueOf(Boolean.TRUE));
        headers.add("deviate",String.valueOf(deviate));
        headers.add("Authorization","123123");
        Map<String, Object> notes = Map.of(
                "text", "This is a sample text.",
                "referenceId", "REF123456",
                "attachments", List.of(
                        Map.of("id", "ATT001", "name", "Document1.pdf"),
                        Map.of("id", "ATT002", "name", "Image1.png")
                )
        );
        restTemplate.exchange(new URI(updateNotes), HttpMethod.POST,new HttpEntity<>(notes,headers),Object.class);
    }
    
    return new ResponseEntity<>(response, HttpStatus.CREATED);
}

// Endpoint to update a work visit order
@PatchMapping("/{orderId}")
public ResponseEntity<Map<String, Object>> updateWorkVisit(
        @PathVariable String orderId,
        @RequestBody Map<String, Object> request) {
    // Logic to handle updating the work visit
    // Depending on the result, return either 204 (No Content) or 202 (Accepted)
    Map<String, Object> response = Map.of(
            "location", "/orders/" + orderId // Example location header
    );
    
    // Example return based on successful update
    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    
    // If no content is appropriate, use:
    // return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
}
}
