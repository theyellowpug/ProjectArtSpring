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

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }
}
