package net.plethora.folvark.models.enumerationmodels;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document("elementPerson")
public class ElementPerson {

    private String id;
    private String name;
    private String value;
    private boolean gender;
    private Date date;


}
