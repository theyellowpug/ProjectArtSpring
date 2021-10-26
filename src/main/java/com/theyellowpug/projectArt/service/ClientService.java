package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        client.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));

        return new User(client.getUsername(), client.getPassword(), authorities);
    }

    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
