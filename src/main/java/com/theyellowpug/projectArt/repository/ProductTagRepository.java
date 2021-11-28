package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
    List<ProductTag> findAllByNameStartsWith(String name);
}
