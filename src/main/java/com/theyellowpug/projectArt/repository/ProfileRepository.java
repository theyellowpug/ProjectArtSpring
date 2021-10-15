package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
