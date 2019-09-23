package com.flat.flatmaintenance.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {
        User user = new User("ala111", "gggg", "Y", "Xsinski", "xxx@wp.pl");
        List<User> users = Arrays.asList(user);

        given(userService.findAll()).willReturn(users);
        mvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username").value("ala111"))
                .andExpect(jsonPath("$[0].password").value("gggg"))
                .andExpect(jsonPath("$[0].firstName").value("Y"))
                .andExpect(jsonPath("$[0].lastName").value("Xsinski"))
                .andExpect(jsonPath("$[0].email").value("xxx@wp.pl"));
    }

    @Test
    public void getUserById() throws Exception {
        User user1 = new User("m1", "m1", "Y", "Xsinski", "xxx@wp.pl");
        Long id = Long.valueOf(1);
        given(userService.findById(id)).willReturn(Optional.of(user1));

        mvc.perform(get("/api/user/{id}" , id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(userService, times(1)).findById(id);


    }
    @Test
    public void saveUser() throws Exception {
        User user1 = new User("m1", "m1", "Y", "Xsinski", "xxx@wp.pl");

        mvc.perform(post("/api/user")
                .content(asJsonString(user1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void getByIdNotFound() throws Exception {
        Long id = Long.valueOf(1);
        mvc.perform(get("/api/user/{id}" , id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}