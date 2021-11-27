package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
}
