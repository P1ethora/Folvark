package net.plethora.folvark.models.system;

import lombok.Getter;
import lombok.Setter;
import net.plethora.folvark.models.ProductMap;

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
