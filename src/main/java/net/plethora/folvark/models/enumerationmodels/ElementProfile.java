package net.plethora.folvark.models.enumerationmodels;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("elementProfile")
public class ElementProfile {

    private String id;
    private String name;
    private String urlName;

}
