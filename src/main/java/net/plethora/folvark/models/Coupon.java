package net.plethora.folvark.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("coupons")
public class Coupon {

    @Id
    private String id;
    private long idUser;
    private int discount;
}
