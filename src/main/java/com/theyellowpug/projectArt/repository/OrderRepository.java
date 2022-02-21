package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orderr,Long> {
}
