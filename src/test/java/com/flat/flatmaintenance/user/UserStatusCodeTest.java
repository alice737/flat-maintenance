package com.flat.flatmaintenance.user;

import com.flat.flatmaintenance.FlatmaintenanceApplication;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FlatmaintenanceApplication.class }, webEnvironment = RANDOM_PORT)
public class UserStatusCodeTest {

    @Autowired
    UserRepository userRepository;

    @Value("${local.server.port}")
    private int port;

    private String baseURI = "http://localhost:";


    @Test
    public void whenRequestGetAll_thenOK(){
        when().request("GET", baseURI+ port+"/api/user").then().statusCode(200);
    }
    @Test
    public void whenRequestGetId_thenNOTFOUND(){
        when().request("GET", baseURI+ port+"/api/user/2").then().statusCode(400);
    }
    @Test
    public void whenRequestDelete_thenNOTFOUND(){
        when().request("DELETE", baseURI+ port+"/api/user/2").then().statusCode(405);
    }
    @Test
    public void whenRequestPOST_thenNOTFOUND(){
        when().request("POST", baseURI+ port+"/api/user").then().statusCode(415);
    }
}
