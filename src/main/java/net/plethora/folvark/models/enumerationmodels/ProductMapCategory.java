package net.plethora.folvark.models.enumerationmodels;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("productMapCategory")
public class ProductMapCategory {

    @Id
    private String id;
    private String nameCategory;
    private String urlNameCategory;
    private String[] subCategories;
    private String urlCover;

}
