package net.plethora.folvark.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document("portalNews")
public class PortalNews {

    @Id
    private String id;
    private String title;
    private String text;
    private Date date;
    private String urlPicture;
    private double ratings;  //оценка
    private int numberRatings; //количество голосов
    private int views;  //количество просмотров
    private String category;

}
