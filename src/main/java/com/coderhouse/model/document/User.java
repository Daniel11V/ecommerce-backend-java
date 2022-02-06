package com.coderhouse.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private String personId;

}
