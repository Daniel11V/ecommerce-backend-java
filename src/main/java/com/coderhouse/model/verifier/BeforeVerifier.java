package com.coderhouse.model.verifier;

import com.coderhouse.model.document.User;
import com.coderhouse.model.exceptions.ApiRestException;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class BeforeVerifier {
    
    @Before("@annotation(com.coderhouse.model.annotations.VerifyUserRegister)")
    public void verifyUserRegister(JoinPoint jp) throws ApiRestException {
        log.info("Before VerifyUserRegister annotation: " + ((User) jp.getSignature()).getEmail());
        var args = jp.getArgs();
        User newUser = (User) args[0];
        if (newUser == null) {
            throw new ApiRestException( "User is null");
        }

        if (newUser.getEmail() == null || newUser.getEmail().isEmpty()) {
            throw new ApiRestException("newUser Email is null");
        }

        if (newUser.getUsername() == null || newUser.getUsername().isEmpty()) {
            throw new ApiRestException("newUser Username is null");
        }

        if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
            throw new ApiRestException("user password is null");
        }
    }        

}
