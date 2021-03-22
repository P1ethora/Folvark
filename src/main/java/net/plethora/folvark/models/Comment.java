package net.plethora.folvark.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document("comments")
public class Comment {

    @Id
    private String id;

    private String idUser;
    private String text;
    private String idResponseTo; //ответ на коммент
    private String attachedTo; //прикреплено к

}