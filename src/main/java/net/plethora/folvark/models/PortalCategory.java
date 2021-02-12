package net.plethora.folvark.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("portalCategory")
public class PortalCategory {

    @Id
    private String id;
    private String nameCategory;
    private String urlNameCategory;

}
