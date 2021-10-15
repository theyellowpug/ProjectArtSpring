package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.Profile;
import com.theyellowpug.projectArt.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/allByClientId")
    public ResponseEntity<Profile> getAllProductsByClientId(@RequestParam("clientId") Long clientId) {
        return ResponseEntity.ok(profileService.getProfileByClientId(clientId));
    }
}
