package com.coderhouse.model.exceptions;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ApiRestTokenException extends Exception {

    private String message;

}
