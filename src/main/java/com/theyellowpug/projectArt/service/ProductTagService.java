package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.ProductTag;
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

    public List<ProductTag> getProductTagsByName(String name) {
        return productTagRepository.findAllByNameStartsWith(name);
    }
}
