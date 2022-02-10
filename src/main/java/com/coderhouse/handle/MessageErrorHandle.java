package com.coderhouse.handle;

import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.exceptions.ApiRestTokenException;
import com.coderhouse.model.exceptions.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MessageErrorHandle {
    Logger logger = LogManager.getLogger(MessageErrorHandle.class);

    @ResponseBody
    @ExceptionHandler(ApiRestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorMessage messageErrorHandle(ApiRestException ex) {
        logger.error(ex);
        return new ErrorMessage(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ApiRestTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorMessage messageErrorHandleToken(ApiRestTokenException ex) {
        logger.error(ex);
        return new ErrorMessage(ex.getMessage());
    }

}
