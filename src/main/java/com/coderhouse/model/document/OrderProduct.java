package com.coderhouse.model.document;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    private int quantity;
    private String productCode;
    private String productDescription;
    private Double unitPrice;

    @Override
    public String toString() {return String.format(
            "OrderProduct[productCode='%s', quantity='%d']", productCode, quantity);
    }
}
