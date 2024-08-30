package com.equinx.poc.controller;

import io.apiwiz.compliance.config.EnableCompliance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableCompliance
@RequestMapping("v1/tickets/v2")
public class Tickets {

@Autowired
private RestTemplate restTemplate;

@Value("${api.create.site.visit:null}")
private String crateVisit;

@Value("${api.cancel.ticket:null}")
private String cancelTicket;
@Value("${api.update.notes:null}")
private String updateNotes;

@Value("${api.ticket.get.ticket:null}")
private String getTicket;

@GetMapping("/tickets/{id}")
public ResponseEntity<Map<String, Object>> getTicketById(@PathVariable String id,
                                                         @RequestHeader(value = "Authorization", required = false) String Authorization,
                                                         @RequestHeader(value = "deviate", required = false) boolean deviate,
                                                         @RequestHeader(value = "enableTracing",required = false) boolean enableTracing) throws URISyntaxException {
    Map<String, Object> data = Map.ofEntries(
            Map.entry("category", "<string>"),
            Map.entry("subCategory", "<string>"),
            Map.entry("description", "<string>"),
            Map.entry("primaryId", "<string>"),
            Map.entry("occurredDateTime", "<dateTime>"),
            Map.entry("id", "<string>"),
            Map.entry("secondaryId", "<string>"),
            Map.entry("customerReferenceId", "<string>"),
            Map.entry("resolutionDateTime", "<dateTime>"),
            Map.entry("status", "CLOSED"),
            Map.entry("resolutions", List.of(
                    Map.ofEntries(
                            Map.entry("id", "<string>"),
                            Map.entry("type", "<string>"),
                            Map.entry("status", "<string>"),
                            Map.entry("description", "<string>"),
                            Map.entry("price", "<string>"),
                            Map.entry("hours", "<string>")
                    ),
                    Map.ofEntries(
                            Map.entry("id", "<string>"),
                            Map.entry("type", "<string>"),
                            Map.entry("status", "<string>"),
                            Map.entry("description", "<string>"),
                            Map.entry("price", "<string>"),
                            Map.entry("hours", "<string>")
                    )
            )),
            Map.entry("details", Map.ofEntries(
                    Map.entry("callFromCage", false),
                    Map.entry("availability", "ANYTIME"),
                    Map.entry("timezone", "America/Puerto_Rico"),
                    Map.entry("submarineEngineerRequired", false)
            )),
            Map.entry("notes", List.of(
                    Map.ofEntries(
                            Map.entry("id", "<string>"),
                            Map.entry("createdDateTime", "<dateTime>"),
                            Map.entry("text", "<string>"),
                            Map.entry("author", "<string>"),
                            Map.entry("referenceId", "<string>"),
                            Map.entry("type", "CUSTOMER_QUERY"),
                            Map.entry("attachments", List.of(
                                    Map.ofEntries(
                                            Map.entry("id", "<string>"),
                                            Map.entry("name", "<string>")
                                    ),
                                    Map.ofEntries(
                                            Map.entry("id", "<string>"),
                                            Map.entry("name", "<string>")
                                    )
                            ))
                    ),
                    Map.ofEntries(
                            Map.entry("id", "<string>"),
                            Map.entry("createdDateTime", "<dateTime>"),
                            Map.entry("text", "<string>"),
                            Map.entry("author", "<string>"),
                            Map.entry("referenceId", "<string>"),
                            Map.entry("type", "TECHNICIAN_QUERY"),
                            Map.entry("attachments", List.of(
                                    Map.ofEntries(
                                            Map.entry("id", "<string>"),
                                            Map.entry("name", "<string>")
                                    ),
                                    Map.ofEntries(
                                            Map.entry("id", "<string>"),
                                            Map.entry("name", "<string>")
                                    )
                            ))
                    )
            )),
            Map.entry("attachments", List.of(
                    Map.ofEntries(
                            Map.entry("id", "<string>"),
                            Map.entry("name", "<string>")
                    ),
                    Map.ofEntries(
                            Map.entry("id", "<string>"),
                            Map.entry("name", "<string>")
                    )
            )),
            Map.entry("contacts", List.of(
                    Map.ofEntries(
                            Map.entry("registeredUser", "<string>"),
                            Map.entry("firstName", "<string>"),
                            Map.entry("lastName", "<string>"),
                            Map.entry("type", "ORDERING"),
                            Map.entry("details", List.of(
                                    Map.ofEntries(
                                            Map.entry("type", "EMAIL"),
                                            Map.entry("value", "<string>")
                                    ),
                                    Map.ofEntries(
                                            Map.entry("type", "EMAIL"),
                                            Map.entry("value", "<string>")
                                    )
                            ))
                    ),
                    Map.ofEntries(
                            Map.entry("registeredUser", "<string>"),
                            Map.entry("firstName", "<string>"),
                            Map.entry("lastName", "<string>"),
                            Map.entry("type", "TECHNICAL"),
                            Map.entry("details", List.of(
                                    Map.ofEntries(
                                            Map.entry("type", "PHONE"),
                                            Map.entry("value", "<string>")
                                    ),
                                    Map.ofEntries(
                                            Map.entry("type", "PHONE"),
                                            Map.entry("value", "<string>")
                                    )
                            ))
                    )
            ))
    );
    return new ResponseEntity<>(data, HttpStatus.OK);
}

@PostMapping("/tickets/{id}/notes")
public ResponseEntity<Map<String, Object>> addNoteToTicket(@PathVariable String id,Map<String,Object> map ,
                                                           @RequestHeader(value = "Authorization", required = false) String Authorization,
                                                           @RequestHeader(value = "deviate", required = false) boolean deviate,
                                                           @RequestHeader(value = "enableTracing",required = false) boolean enableTracing) throws URISyntaxException {
    if(enableTracing){
        HttpHeaders headers = new HttpHeaders();
        headers.add("enableTracing",String.valueOf(Boolean.TRUE));
        headers.add("deviate",String.valueOf(deviate));
        headers.add("Authorization","123123");
        restTemplate.exchange(new URI(getTicket), HttpMethod.GET,new HttpEntity<>(headers),Object.class);
    }
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
}
@PostMapping("/tickets/{id}")
public ResponseEntity<Map<String, Object>> updateATicket(@PathVariable String id, Map<String,Object> map,
                                                         @RequestHeader(value = "deviate", required = false) boolean deviate,
                                                         @RequestHeader(value = "enableTracing",required = false) boolean enableTracing) throws URISyntaxException {
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
}
@PostMapping("/tickets/{id}/cancel")
public ResponseEntity<Map<String, Object>> cancelTicket(@PathVariable String id,Map<String , Object> map,
                                                        @RequestHeader(value = "deviate", required = false) boolean deviate,
                                                        @RequestHeader(value = "Authorization", required = false) String Authorization,
                                                        @RequestHeader(value = "enableTracing",required = false) boolean enableTracing) throws URISyntaxException {
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
}
@PostMapping("/tickets")
public ResponseEntity<Map<String, Object>> createATicket(Map<String , Object> map,
                                                         @RequestHeader(value = "deviate", required = false) boolean deviate,
                                                         @RequestHeader(value = "Authorization", required = false) String Authorization,
                                                         @RequestHeader(value = "enableTracing",required = false) boolean enableTracing) throws URISyntaxException {
    if(enableTracing){
        HttpHeaders headers = new HttpHeaders();
        headers.add("enableTracing",String.valueOf(Boolean.TRUE));
        headers.add("deviate",String.valueOf(deviate));
        headers.add("Authorization","123123");
        Map<String, Object> dataMap = new HashMap<>();
        
        dataMap.put("details", Map.of(
                "cages", List.of(
                        Map.of("id", "<string>", "accountNumber", "<string>", "cabinetId", "<string>"),
                        Map.of("id", "<string>", "accountNumber", "<string>", "cabinetId", "<string>")
                ),
                "visitStartDateTime", "<dateTime>",
                "visitEndDateTime", "<dateTime>",
                "visitors", List.of(
                        Map.of("registeredUsers", List.of("<string>", "<string>")),
                        Map.of("registeredUsers", List.of("<string>", "<string>"))
                ),
                "openCabinet", false
        ));
        
        dataMap.put("description", "<string>");
        dataMap.put("customerReferenceId", "<string>");
        dataMap.put("purchaseOrder", Map.of(
                "type", "NEW",
                "number", "<string>",
                "amount", "<number>",
                "startDate", "<date>",
                "endDate", "<date>",
                "attachmentId", "<string>"
        ));
        dataMap.put("attachments", List.of(
                Map.of("id", "<string>", "name", "<string>"),
                Map.of("id", "<string>", "name", "<string>")
        ));
        dataMap.put("contacts", List.of(
                Map.of(
                        "registeredUsers", List.of("<string>", "<string>"),
                        "type", "NOTIFICATION",
                        "availability", "ANYTIME",
                        "timezone", "America/Caracas"
                ),
                Map.of(
                        "registeredUsers", List.of("<string>", "<string>"),
                        "type", "NOTIFICATION",
                        "availability", "ANYTIME",
                        "timezone", "Australia/NSW"
                )
        ));
        if(deviate){
            Map<String, Object> notes = Map.of(
                    "text", "This is a sample text.",
                    "referenceId", "REF123456",
                    "attachments", List.of(
                            Map.of("id", "ATT001", "name", "Document1.pdf"),
                            Map.of("id", "ATT002", "name", "Image1.png")
                    )
            );
            restTemplate.exchange(new URI(updateNotes), HttpMethod.POST,new HttpEntity<>(notes,headers),Object.class);
        } else {
            restTemplate.exchange(new URI(crateVisit), HttpMethod.POST,new HttpEntity<>(dataMap,headers),Object.class);
        }
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
}
}