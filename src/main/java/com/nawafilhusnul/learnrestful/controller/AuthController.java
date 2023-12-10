package com.nawafilhusnul.learnrestful.controller;

import com.nawafilhusnul.learnrestful.model.LoginUserRequest;
import com.nawafilhusnul.learnrestful.model.TokenResponse;
import com.nawafilhusnul.learnrestful.model.WebResponse;
import com.nawafilhusnul.learnrestful.service.AuthService;
import com.nawafilhusnul.learnrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request) {
        TokenResponse tokenResponse = authService.login(request);

        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }
}
