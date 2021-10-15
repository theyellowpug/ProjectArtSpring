package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.Service;
import com.theyellowpug.projectArt.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/service")
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping("/")
    public ResponseEntity<Service> getProductById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @GetMapping("/allByClientId")
    public ResponseEntity<List<Service>> getAllProductsByClientId(@RequestParam("clientId") Long clientId) {
        return ResponseEntity.ok(serviceService.getAllServicesByClientId(clientId));
    }
}
