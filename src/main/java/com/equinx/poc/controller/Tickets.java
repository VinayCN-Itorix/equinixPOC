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
            Map.entry("category", "Technical Issue"),
            Map.entry("subCategory", "Network"),
            Map.entry("description", "Network outage affecting multiple users."),
            Map.entry("primaryId", "PRI12345"),
            Map.entry("occurredDateTime", "2024-08-30T14:00:00Z"),
            Map.entry("id", "ISSUE67890"),
            Map.entry("secondaryId", "SEC09876"),
            Map.entry("customerReferenceId", "CUST54321"),
            Map.entry("resolutionDateTime", "2024-08-30T16:00:00Z"),
            Map.entry("status", "CLOSED"),
            Map.entry("resolutions", List.of(
                    Map.ofEntries(
                            Map.entry("id", "RES001"),
                            Map.entry("type", "Fix"),
                            Map.entry("status", "Completed"),
                            Map.entry("description", "Replaced faulty network switch."),
                            Map.entry("price", "$500"),
                            Map.entry("hours", "2")
                    ),
                    Map.ofEntries(
                            Map.entry("id", "RES002"),
                            Map.entry("type", "Diagnosis"),
                            Map.entry("status", "Completed"),
                            Map.entry("description", "Diagnosed network configuration issue."),
                            Map.entry("price", "$300"),
                            Map.entry("hours", "1.5")
                    )
            )),
            Map.entry("details", Map.ofEntries(
                    Map.entry("callFromCage", true),
                    Map.entry("availability", "ANYTIME"),
                    Map.entry("timezone", "America/Puerto_Rico"),
                    Map.entry("submarineEngineerRequired", false)
            )),
            Map.entry("notes", List.of(
                    Map.ofEntries(
                            Map.entry("id", "NOTE001"),
                            Map.entry("createdDateTime", "2024-08-30T15:00:00Z"),
                            Map.entry("text", "Initial report received from customer."),
                            Map.entry("author", "Alice Johnson"),
                            Map.entry("referenceId", "REF001"),
                            Map.entry("type", "CUSTOMER_QUERY"),
                            Map.entry("attachments", List.of(
                                    Map.ofEntries(
                                            Map.entry("id", "ATT001"),
                                            Map.entry("name", "NetworkDiagram.png")
                                    ),
                                    Map.ofEntries(
                                            Map.entry("id", "ATT002"),
                                            Map.entry("name", "LogFile.txt")
                                    )
                            ))
                    ),
                    Map.ofEntries(
                            Map.entry("id", "NOTE002"),
                            Map.entry("createdDateTime", "2024-08-30T15:30:00Z"),
                            Map.entry("text", "Technician update on issue resolution."),
                            Map.entry("author", "Bob Smith"),
                            Map.entry("referenceId", "REF002"),
                            Map.entry("type", "TECHNICIAN_QUERY"),
                            Map.entry("attachments", List.of(
                                    Map.ofEntries(
                                            Map.entry("id", "ATT003"),
                                            Map.entry("name", "ResolutionSteps.docx")
                                    ),
                                    Map.ofEntries(
                                            Map.entry("id", "ATT004"),
                                            Map.entry("name", "Invoice.pdf")
                                    )
                            ))
                    )
            )),
            Map.entry("attachments", List.of(
                    Map.ofEntries(
                            Map.entry("id", "ATT005"),
                            Map.entry("name", "DetailedReport.pdf")
                    ),
                    Map.ofEntries(
                            Map.entry("id", "ATT006"),
                            Map.entry("name", "ClientFeedback.xlsx")
                    )
            )),
            Map.entry("contacts", List.of(
                    Map.ofEntries(
                            Map.entry("registeredUser", "user123"),
                            Map.entry("firstName", "John"),
                            Map.entry("lastName", "Doe"),
                            Map.entry("type", "ORDERING"),
                            Map.entry("details", List.of(
                                    Map.ofEntries(
                                            Map.entry("type", "EMAIL"),
                                            Map.entry("value", "john.doe@example.com")
                                    ),
                                    Map.ofEntries(
                                            Map.entry("type", "PHONE"),
                                            Map.entry("value", "+1122334455")
                                    )
                            ))
                    ),
                    Map.ofEntries(
                            Map.entry("registeredUser", "tech456"),
                            Map.entry("firstName", "Jane"),
                            Map.entry("lastName", "Smith"),
                            Map.entry("type", "TECHNICAL"),
                            Map.entry("details", List.of(
                                    Map.ofEntries(
                                            Map.entry("type", "EMAIL"),
                                            Map.entry("value", "jane.smith@tech.com")
                                    ),
                                    Map.ofEntries(
                                            Map.entry("type", "PHONE"),
                                            Map.entry("value", "+9988776655")
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
                "number", "234567890",
                "amount", "698990",
                "startDate", "12-09-24",
                "endDate", "12-10-24",
                "attachmentId", "987654356789"
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