package net.plethora.folvark.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("productMapCategory")
public class ProductMapCategory {

    private String nameCategory;
    private String urlNameCategory;
    private String[] subCategories;
    private String urlCover;

}
