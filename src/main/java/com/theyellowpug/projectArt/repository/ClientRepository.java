package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String clientName);
}
