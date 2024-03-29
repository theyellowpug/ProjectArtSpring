package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.dTO.ClientRegistrationDTO;
import com.theyellowpug.projectArt.entity.Cart;
import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Profile;
import com.theyellowpug.projectArt.model.UserRole;
import com.theyellowpug.projectArt.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        client.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));

        return new User(client.getEmail(), client.getPassword(), authorities);
    }

    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Long getClientIdByEmail(String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        return client.getId();
    }

    public Boolean getIfClientIsArtist(Long id) {
        Optional<Client> c = clientRepository.findById(id);
        try{
            return c.get().getIsArtist();
        }catch (Exception x){ return false; }

    }

    public void createClient(ClientRegistrationDTO clientRegistrationDTO) {

        Profile profile = Profile.builder()
                .firstname(clientRegistrationDTO.getFirstname())
                .lastname(clientRegistrationDTO.getLastname())
                .nickname(clientRegistrationDTO.getFirstname() + " " + clientRegistrationDTO.getLastname())
                .dateOfBirth(clientRegistrationDTO.getDateOfBirth())
                .build();

        Cart cart = Cart.builder().build();

        Client client = Client.builder()
                .email(clientRegistrationDTO.getEmail())
                .password(passwordEncoder.encode(clientRegistrationDTO.getPassword()))
                .role(UserRole.ROLE_CLIENT)
                .isArtist(false)
                .profile(profile)
                .cart(cart)
                .build();

        profile.setClient(client);
        cart.setClient(client);
        clientRepository.save(client);
    }

    public void addRoleByClientId(Long id, UserRole userRole) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Set<UserRole> roles = client.getRoles();
        roles.add(userRole);
        clientRepository.save(client);
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    public String setIsArtist(Long clientId, Boolean isArtist) {
        Client client = clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);
        client.setIsArtist(isArtist);
        clientRepository.save(client);
        return "isArtist set to " + isArtist + " in client with id:" + clientId;
    }

}
