package com.coderhouse.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("person")
public class Person {

    @Id
    private String id;
    private String personId;
    private String names;
    private String fatherName;
    private String motherName;

}
