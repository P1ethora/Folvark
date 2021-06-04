package net.plethora.folvark.models.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("favorites")
public class FavoritesPack {

    @Id
    private String id;
    private List<String> idFavorites;

}