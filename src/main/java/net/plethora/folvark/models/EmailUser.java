package net.plethora.folvark.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("emailUser")
public class EmailUser {

    @Id
    private String id;
    private String email;

    public EmailUser(String email) {
        this.email = email;
    }

    public EmailUser() {
    }

}