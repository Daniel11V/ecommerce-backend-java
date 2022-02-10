package com.coderhouse.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {

    private String productCode;
    private int quantity;

    @Override
    public String toString() {return String.format(
            "CartProduct[productCode='%s', quantity='%d']", productCode, quantity);
    }
}
