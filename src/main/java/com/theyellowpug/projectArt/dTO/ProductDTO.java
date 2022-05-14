package com.theyellowpug.projectArt.dTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.theyellowpug.projectArt.entity.Comment;
import com.theyellowpug.projectArt.entity.ProductTag;
import com.theyellowpug.projectArt.model.ProductStatus;
import com.theyellowpug.projectArt.model.ProductType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class ProductDTO {

    private Long id;

    private Long ownerId;

    private ProductType productType;

    private String name;

    private Long price;

    private String description;

    private ProductStatus productStatus;

    private Long quantity;

    private String images;

    private List<Comment> comments;

    private List<ProductTag> productTags;
}
