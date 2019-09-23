package com.flat.flatmaintenance.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;


    private void createTestUser(String name){
        User user = new User(name, "12345", "X", "Mysikiewicz", "u123@onet.pl");
        repository.save(user);
    }

    @Test
    public void givenUser_whenGetUser_thenStatus200()
            throws Exception {

        createTestUser("bob");

        mvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].username", is("Bob")));
    }


    @Test
    public void givenUser_whenGetUser_thenStatus400()
            throws Exception {

        mvc.perform(get("/api/user/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
}
