package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ClientRepository clientRepository;

    public com.theyellowpug.projectArt.entity.Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<com.theyellowpug.projectArt.entity.Service> getAllServicesByClientId(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return client.getServices();
    }
}
