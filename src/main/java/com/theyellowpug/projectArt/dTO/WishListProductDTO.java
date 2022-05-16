package com.theyellowpug.projectArt.dTO;

import com.theyellowpug.projectArt.model.ProductStatus;
import com.theyellowpug.projectArt.model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WishListProductDTO {

    private Long id;

    private ProductType productType;

    private String name;

    private Long price;

    private String images;

    private ProductStatus productStatus;

    private Long quantity;


}
