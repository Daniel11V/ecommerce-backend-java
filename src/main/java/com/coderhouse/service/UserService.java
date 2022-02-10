package com.coderhouse.service;

import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;

public interface UserService {
    UserResponse getUser(UserRequest request) throws Exception;
    UserResponse register(UserRequest request) throws Exception;
}
