package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
