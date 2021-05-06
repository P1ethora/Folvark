package net.plethora.folvark.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@Document("comments")
public class Comment {

    @Id
    private String id;

    private Long idUser;
    private String text;
    private String idResponseTo; //ответ на коммент
    private String attachedTo; //прикреплено к
    private Date date;
    private List<Comment> answers;

}