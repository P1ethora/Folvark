package net.plethora.folvark.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("MapRequest")
public class MapRequest {

    @Id
    private String id;
    private String idUser;
    private String country;
    private String region;
    private String city;
    private String age;
    private String type;
    private String comment;

}
