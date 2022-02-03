package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.dTO.ProfileCardDTO;
import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Profile;
import com.theyellowpug.projectArt.exception.ImgToDBException;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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

    public void setProfileImage(Long id, MultipartFile data) throws IOException {
        String folder = "/profilePics/";
        File dir = new File(folder);
        if(!dir.exists()){
            dir.mkdir();
        }
        byte[] imgData = data.getBytes();
        Path path = Paths.get(folder + id + ".jpg");
        try {
            Files.write(path, imgData);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new IOException(ex);
        }
    }

    public ResponseEntity<Object> getProfilePic(Long id){
        try {
            File file = new File("/profilePics/" + id + ".jpg");
            byte[] imgBytes = Files.readAllBytes(file.toPath());
            String base64imgData = Base64.getEncoder().encodeToString(imgBytes);
            //InputStreamResource resource = new InputStreamResource(new FileInputStream(file)); // returning as resource is optimal for file downloads but not showing img in browser!
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("image/jpeg")).body(base64imgData);
            return responseEntity;
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
