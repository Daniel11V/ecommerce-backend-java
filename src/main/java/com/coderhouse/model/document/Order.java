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
@Document("order")
public class Order {

    @Id
    private String id;
    private String orderCode;
    private String email;
    private String state;
    private Date creationDate;
    @Builder.Default
    private List<OrderProduct> items = new ArrayList<>();
}
