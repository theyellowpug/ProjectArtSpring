package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.ProductTag;
import com.theyellowpug.projectArt.repository.ProductRepository;
import com.theyellowpug.projectArt.repository.ProductTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTagService {

    private final ProductTagRepository productTagRepository;

    public List<ProductTag> getAllProductTags() {
        return productTagRepository.findAll();
    }

    public List<ProductTag> getProductTagsByNameStartsWith(String name) {
        return productTagRepository.findAllByNameStartsWith(name);
    }

    public String createProductTag(String name) {
        productTagRepository.save(ProductTag.builder().name(name).build());
        return "New tag was successfully created: " + name;
    }
}
