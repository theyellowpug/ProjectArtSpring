package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByProductType(ProductType productType, Pageable pageable);
}
