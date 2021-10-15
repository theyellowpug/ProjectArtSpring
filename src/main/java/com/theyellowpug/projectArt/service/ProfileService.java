package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Profile;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ProfileService {
    public final ProfileRepository profileRepository;
    public final ClientRepository clientRepository;

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public Profile getProfileByClientId(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return client.getProfile();
    }
}
