package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.dTO.ProfileCardDTO;
import com.theyellowpug.projectArt.entity.Profile;
import com.theyellowpug.projectArt.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/")
    public ResponseEntity<Profile> getProfileById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @GetMapping("/byClientId")
    public ResponseEntity<Profile> getProfileClientId(@RequestParam("clientId") Long clientId) {
        return ResponseEntity.ok(profileService.getProfileByClientId(clientId));
    }

    @GetMapping("/profileCard")
    public ResponseEntity<List<ProfileCardDTO>> getProfileCards(@RequestParam("numberOfPages") Long numberOfPages,
                                                                @RequestParam("numberOfProfiles") Long numberOfProfiles) {
        return ResponseEntity.ok(profileService.getProfileCards(numberOfPages, numberOfProfiles));
    }
}
