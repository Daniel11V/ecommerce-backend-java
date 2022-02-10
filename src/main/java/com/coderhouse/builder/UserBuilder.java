package com.coderhouse.builder;

import com.coderhouse.model.document.User;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;

public class UserBuilder {

    public static User requestToDocument(UserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
    }

    public static UserResponse documentToResponse(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
