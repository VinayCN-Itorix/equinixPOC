package com.equinx.poc.controller;

import io.apiwiz.compliance.config.EnableCompliance;
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
@EnableCompliance
@RequestMapping("/resourceManager/v1/metalOrganizations")
public class Org {
@Autowired
private RestTemplate restTemplate;

@Value("${api.create.user:null}")
private String createUser;

    @PostMapping
    public ResponseEntity<?> createOrg(@RequestBody Map<String,Object> map,
                                       @RequestHeader(value = "deviate", required = false) boolean deviate,
                                       @RequestHeader(value = "enableTracing",required = false) boolean enableTracing) throws URISyntaxException{
       if(enableTracing){
           Map<String, Object> dataMap = Map.ofEntries(
                   Map.entry("firstName", "John"),
                   Map.entry("lastName", "Doe"),
                   Map.entry("companyName", "Tech Solutions"),
                   Map.entry("contactDetails", List.of(
                           Map.of("type", "EMAIL", "value", "john.doe@example.com"),
                           Map.of("type", "PHONE", "value", "+1234567890")
                   )),
                   Map.entry("username", "john"),
                   Map.entry("localName", "יוחנן"),
                   Map.entry("companyLocalName", "טק פתרונות"),
                   Map.entry("title", "Software Engineer"),
                   Map.entry("department", "Development"),
                   Map.entry("deactivationDateTime", "2024-12-31T23:59:59Z"),
                   Map.entry("timezone", "Asia/Jerusalem"),
                   Map.entry("locale", "EN_US"),
                   Map.entry("activationDateTime", "2024-01-01T00:00:00Z"),
                   Map.entry("createdDateTime", "2023-01-01T12:00:00Z"),
                   Map.entry("updatedDateTime", "2024-01-01T12:00:00Z"),
                   Map.entry("updatedByFullName", "Jane Smith"),
                   Map.entry("deletedDateTime", null)
           );
           
           HttpHeaders headers = new HttpHeaders();
           headers.add("enableTracing",String.valueOf(Boolean.TRUE));
           headers.add("deviate",String.valueOf(deviate));
           restTemplate.exchange(new URI(createUser), HttpMethod.POST,new HttpEntity<>(dataMap,headers),Object.class);
       }
       
       
       return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
