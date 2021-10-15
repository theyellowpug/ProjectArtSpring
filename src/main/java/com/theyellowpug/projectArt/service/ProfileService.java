package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Profile;
import com.theyellowpug.projectArt.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor
public class ProfileService {
    public final ProfileRepository profileRepository;

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElseThrow(EntityExistsException::new);
    }
}
