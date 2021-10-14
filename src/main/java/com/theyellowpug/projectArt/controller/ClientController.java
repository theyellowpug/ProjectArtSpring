package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientService.getAllClient();
    }

    @GetMapping("/{id}")
    public Client getAllClients(@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }

}
