package net.plethora.folvark.models;

import lombok.Data;

import java.util.Date;

@Data
public class Reply {

    private String id;
    private String urlPhoto;
    private String name;
    private Long idUser;
    private String text;
    private String idParent;  //Основной комментарий
    private String idComment; //На который пришел ответ
    private Date date;

}
