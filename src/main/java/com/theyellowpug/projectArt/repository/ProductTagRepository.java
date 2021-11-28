package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
    List<ProductTag> findAllByNameStartsWith(String name);

    Optional<ProductTag> findByName(String name);
}
