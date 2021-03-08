package net.plethora.folvark.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckedCartProduct {

    private boolean availability;
    private ProductMap productMap;

    public CheckedCartProduct(ProductMap productMap, boolean availability) {
        this.productMap = productMap;
        this.availability = availability;
    }

    public CheckedCartProduct() {
    }

}
