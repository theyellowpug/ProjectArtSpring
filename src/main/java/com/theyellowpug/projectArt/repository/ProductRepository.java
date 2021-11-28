package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.entity.ProductTag;
import com.theyellowpug.projectArt.model.ProductType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductType(ProductType productType, Pageable pageable);

    List<Product> findAllByProductTypeAndNameContains(ProductType productType, String name);

    List<Product> findAllByProductTypeAndProductTags(ProductType productType, ProductTag productTag);
}
