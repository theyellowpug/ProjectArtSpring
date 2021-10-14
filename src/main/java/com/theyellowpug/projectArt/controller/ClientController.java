package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
