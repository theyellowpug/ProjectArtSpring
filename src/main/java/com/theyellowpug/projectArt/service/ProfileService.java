package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.dTO.ProfileCardDTO;
import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Profile;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProfileCardDTO> getProfileCards(Long numberOfPages, Long numberOfProfiles) {
        Pageable pageable = PageRequest.of(numberOfPages.intValue(), numberOfProfiles.intValue());

        List<Client> clients = clientRepository.findAllByIsArtist(true, pageable);

        List<Profile> profiles = clients.stream().map(client -> client.getProfile()).collect(Collectors.toList());

        List<ProfileCardDTO> profileCardDTOS = profiles.stream().map(profile -> ProfileCardDTO.builder()
                        .clientId(profile.getClient().getId())
                        .id(profile.getId())
                        .nickname(profile.getNickname())
                        .title(profile.getTitle())
                        .shortDescription(profile.getShortDescription())
                        .build())
                .collect(Collectors.toList());

        return profileCardDTOS;
    }
}
