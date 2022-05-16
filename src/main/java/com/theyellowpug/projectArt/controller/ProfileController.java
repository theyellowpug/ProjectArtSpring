package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.dTO.ProfileCardDTO;
import com.theyellowpug.projectArt.entity.Profile;
import com.theyellowpug.projectArt.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PutMapping("/setProfilePic")
    public ResponseEntity<Object> setProfilePic(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") Long id
    ){
        try {
            profileService.setProfileImage(id, file);
            return ResponseEntity.ok("Succesfully updated profile pic of ID: " + id);
        } catch (IOException ex) { return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
    }

    @GetMapping("/getProfilePic")
    public ResponseEntity<Object> getProfilePic(
            @RequestParam("id") Long id
    ){
        return profileService.getProfilePic(id);
    }

    @GetMapping("hasProfilePic")
    public ResponseEntity<Object> hasProfilePic(
            @RequestParam("id") Long id
    ){
        return profileService.hasProfilePic(id);
    }
}
