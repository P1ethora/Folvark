package net.plethora.folvark.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("productMap")
public class ProductMap {

    @Id
    private String id;
    private String name;       //имя
    private String urlPicture; //путь к картинке
    private String urlDownload;// путь к файлу
    private String price;      //цена
    private String scale;      //маштаб
    private String data;       //год
    private String country;    //страна
    private String city;       //Город
    private boolean coordinates; //привязка к координатам
    private String description; //Описание
    private double ratings;  //оценка
    private int numberRatings;//количество голосов
    private int views; //количество просмотров
    private String category; //категория товара

}
