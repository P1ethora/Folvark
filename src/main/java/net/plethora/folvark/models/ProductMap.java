package net.plethora.folvark.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("productMap")
public class ProductMap {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;       //имя

    @JsonProperty("urlPicture")
    private String urlPicture; //путь к картинке
    @JsonIgnore
    private String urlDownload;// путь к файлу
    @JsonProperty("price")
    private String price;      //цена
    @JsonIgnore
    private String scale;      //маштаб
    @JsonIgnore
    private String data;       //год
    @JsonIgnore
    private String country;    //страна
    @JsonIgnore
    private String city;       //Город
    @JsonIgnore
    private boolean coordinates; //привязка к координатам
    @JsonIgnore
    private String description; //Описание
    @JsonIgnore
    private double ratings;  //оценка
    @JsonIgnore
    private int numberRatings;//количество голосов
    @JsonIgnore
    private int views; //количество просмотров
    @JsonIgnore
    private String category; //категория товара

    private String idComments;

    public ProductMap() {
    }

    @Override
    public String toString() {
        return "ProductMap{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", urlPicture='" + urlPicture + '\'' +
                ", urlDownload='" + urlDownload + '\'' +
                ", price='" + price + '\'' +
                ", scale='" + scale + '\'' +
                ", data='" + data + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", coordinates=" + coordinates +
                ", description='" + description + '\'' +
                ", ratings=" + ratings +
                ", numberRatings=" + numberRatings +
                ", views=" + views +
                ", category='" + category + '\'' +
                '}';
    }
}