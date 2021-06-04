package net.plethora.folvark.models.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.state.ProductState;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CheckedCartProduct {

    private ProductState productState;
    private ProductMap productMap;

}
