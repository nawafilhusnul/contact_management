package com.nawafilhusnul.learnrestful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nawafilhusnul.learnrestful.entity.User;
import com.nawafilhusnul.learnrestful.model.RegisterUserRequest;
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
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setPassword("secret");
        request.setName("test");
        request.setUsername("test");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                MockMvcResultMatchers
                        .status()
                        .isOk()
        ).andDo(
                result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {

                    });

                    assertEquals("OK", response.getData());
                }
        );
    }

    @Test
    void testRegisterBadRequest() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setPassword("");
        request.setName("");
        request.setUsername("");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                MockMvcResultMatchers
                        .status()
                        .isBadRequest()
        ).andDo(
                result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {

                    });

                    assertNotNull(response.getErrors());
                }
        );
    }

    @Test
    void testRegisterDuplicate() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setName("test");
        userRepository.save(user);

        RegisterUserRequest request = new RegisterUserRequest();
        request.setPassword("secret");
        request.setName("test");
        request.setUsername("test");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                MockMvcResultMatchers
                        .status()
                        .isBadRequest()
        ).andDo(
                result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {

                    });

                    assertNotNull(response.getErrors());
                }
        );
    }
}
