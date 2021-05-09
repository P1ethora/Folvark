package net.plethora.folvark.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
@Data
@Document("comments")
public class Comment {

    @Id
    private String id;

    private String name;
    private Long idUser;
    private String text;
    private String attachedTo; //прикреплено к
    private Date date;
    private List<Reply> answers;

}