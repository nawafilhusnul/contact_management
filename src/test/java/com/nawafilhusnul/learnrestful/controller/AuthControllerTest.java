package com.nawafilhusnul.learnrestful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nawafilhusnul.learnrestful.entity.User;
import com.nawafilhusnul.learnrestful.model.LoginUserRequest;
import com.nawafilhusnul.learnrestful.model.RegisterUserRequest;
import com.nawafilhusnul.learnrestful.model.TokenResponse;
import com.nawafilhusnul.learnrestful.model.WebResponse;
import com.nawafilhusnul.learnrestful.repository.UserRepository;
import com.nawafilhusnul.learnrestful.security.BCrypt;
import jdk.jfr.Registered;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLoginFailedUserNotFound() throws Exception {
        LoginUserRequest request = new LoginUserRequest();
        request.setPassword("test");
        request.setUsername("test");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                MockMvcResultMatchers
                        .status()
                        .isUnauthorized()
        ).andDo(
                result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {

                    });

                    assertNotNull(response.getErrors());
                }
        );
    }

    @Test
    void testLoginFailedWrongPassword() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setName("test");
        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setPassword("test");
        request.setUsername("test");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                MockMvcResultMatchers
                        .status()
                        .isUnauthorized()
        ).andDo(
                result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {

                    });

                    assertNotNull(response.getErrors());
                }
        );
    }

    @Test
    void testLoginSuccess() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setName("test");
        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setPassword("rahasia");
        request.setUsername("test");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                MockMvcResultMatchers
                        .status()
                        .isOk()
        ).andDo(
                result -> {
                    WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<TokenResponse>>() {

                    });

                    assertNull(response.getErrors());
                    assertNotNull(response.getData().getExpiredAt());

                    User userDb = userRepository.findById("test").orElse(null);
                    assertNotNull(userDb);
                    assertEquals(userDb.getToken(), response.getData().getToken());
                    assertEquals(userDb.getTokenExpiredAt(), response.getData().getExpiredAt());
                }
        );
    }

}
