package net.plethora.folvark.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartPackage {

    private List<ProductMap> productMaps;
    private double allPrice;

}