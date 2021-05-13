package net.plethora.folvark.models.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.plethora.folvark.models.ProductMap;

import java.util.List;

@Data
@AllArgsConstructor
public class CartPackage {

    private List<ProductMap> productMaps;
    private double allPrice;

}