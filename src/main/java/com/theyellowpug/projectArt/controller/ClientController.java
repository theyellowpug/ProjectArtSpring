package com.theyellowpug.projectArt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.dTO.ClientRegistrationDTO;
import com.theyellowpug.projectArt.model.UserRole;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClient());
    }

    @GetMapping("/")
    public ResponseEntity<Client> getClientById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping("/")
    public void createClient(@RequestBody ClientRegistrationDTO clientRegistrationDTO) {
        clientService.createClient(clientRegistrationDTO);
    }

    @PutMapping("/addRoleByClientId")
    public void addRoleByClientId(@RequestParam("id") Long id, @RequestParam("userRole") UserRole userRole) {
        clientService.addRoleByClientId(id, userRole);
    }

    @DeleteMapping("/")
    public void deleteClientById(@RequestParam("id") Long id) {
        clientService.deleteClientById(id);
    }


    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());

                String secret = "secret"; // hide
                Algorithm signingAlgorithm = Algorithm.HMAC512(secret.getBytes());

                JWTVerifier jwtVerifier = JWT.require(signingAlgorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);

                String email = decodedJWT.getSubject();
                Client client = clientRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);

                String access_token = JWT.create()
                        .withSubject(client.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", client.getRoles().stream().map(userRole -> userRole.toString()).collect(Collectors.toList()))
                        .sign(signingAlgorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
