package com.coderhouse.service.impl;

import com.coderhouse.builder.UserBuilder;
import com.coderhouse.cache.CacheClient;
import com.coderhouse.model.annotations.VerifyUserRegister;
import com.coderhouse.model.document.User;
import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.repository.UserRepository;
import com.coderhouse.security.JwtProvider;
import com.coderhouse.service.EmailService;
import com.coderhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CacheClient<String> cache;
    private final EmailService emailService;

    @Override
    public UserResponse getUser(UserRequest request) throws ApiRestException {

        var user = getByEmail(request.getEmail());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ApiRestException("El email o el password es inválido");
        }

        var dataFromCache = cache.recover(user.getUsername(), String.class);
        if (!Objects.isNull(dataFromCache)) {
            return UserResponse.builder().username(user.getUsername()).token(dataFromCache).build();
        }

        var token = jwtProvider.getJWTToken(request.getEmail());
        cache.save(user.getUsername(), token);
        return UserResponse.builder().username(user.getUsername()).token(token).build();
    }

    @VerifyUserRegister
    @Override
    public UserResponse register(UserRequest request) throws ApiRestException {
        validateUser(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        var user = repository.save(UserBuilder.requestToDocument(request));
        emailService.sendNewRegisterEmail();
        return UserBuilder.documentToResponse(user);
    }

    void validateUser(UserRequest request) throws ApiRestException {
        var user = getByUsername(request.getUsername());
        if (user != null) {
            throw new ApiRestException("El usuario ya existe");
        }
        user = repository.findUserByEmail(request.getEmail());
        if (user != null) {
            throw new ApiRestException("El correo ya se encuentra registrado");
        }
    }

    private User getByUsername(String username) {
        return repository.findUserByUsername(username);
    }
    private User getByEmail(String email) {
        return repository.findUserByEmail(email);
    }
}
