package com.coderhouse.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("cart")
public class Cart {

    @Id
    private String id;
    private String cartCode;
    private String email;
    private String deliverAddress;
    private Date creationDate;
    @Builder.Default
    private List<CartProduct> items = new ArrayList<>();
}
