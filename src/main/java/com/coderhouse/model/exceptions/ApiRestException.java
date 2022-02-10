package com.coderhouse.model.exceptions;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor()
public class ApiRestException extends Exception {

    private String message;

}
