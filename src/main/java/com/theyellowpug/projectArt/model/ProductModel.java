package com.theyellowpug.projectArt.model;

import lombok.Data;

@Data
public class ProductModel {

    private ProductType productType;

    private String name;

    private Long price;

    private String description;
}
