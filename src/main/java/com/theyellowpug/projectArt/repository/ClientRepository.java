package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    Optional<Client> findById(Long id);

    List<Client> findAllByIsArtist(Boolean isArtist, Pageable pageable);
}
