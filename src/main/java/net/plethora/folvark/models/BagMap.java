package net.plethora.folvark.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@Document("bags")
public class BagMap {

    @Id
    private String id;
    private ArrayList<String> maps;
}