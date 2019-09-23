package com.flat.flatmaintenance.product;

import com.flat.flatmaintenance.FlatmaintenanceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FlatmaintenanceApplication.class }, webEnvironment = RANDOM_PORT)
public class ProductControllerTest {
    @Value("${local.server.port}")
    private int port;

    private String baseURI = "http://localhost:";
    @Test
    public void whenRequestGetAll_thenOK(){
        when().request("GET", baseURI+ port+"/api/products").then().statusCode(200);
    }

}