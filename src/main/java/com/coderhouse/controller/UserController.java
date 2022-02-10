package com.coderhouse.controller;

import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ecommerce")
public class UserController {

    private final UserService service;

    @PostMapping("user/login")
    public UserResponse login(@RequestBody UserRequest request) throws Exception {
        return service.getUser(request);
    }

    @PostMapping("user")
    public UserResponse register(@Validated @RequestBody UserRequest request) throws Exception {
        return service.register(request);
    }

}
