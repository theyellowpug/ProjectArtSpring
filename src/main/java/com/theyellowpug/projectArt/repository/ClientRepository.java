package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
