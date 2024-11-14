package com.deliverynow.user.adapters.controller;

import com.deliverynow.user.mock.CustomerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestHTTPEndpoint(CustomerRestController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerRestControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        RestAssured.basePath = "/application--deliverynow-user";
    }

    @Test
    @Order(1)
    void mustInsertClientTest() throws JsonProcessingException {
        var customerRequest = CustomerFactory.getCustomerRequest("10322308003");
        var json = objectMapper.writeValueAsBytes(customerRequest);

        given()
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post("/customer")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void mustGetClientTest() {
        given()
                .queryParam("document", "10322308003")
                .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .body("data.document", equalTo("10322308003"))
                .body("data.name", equalTo("Mario"))
                .body("data.email", equalTo("word@example.com"));
    }

    @Test
    @Order(3)
    void mustGetClientSessionTest() {

        given()
                .header("Authorization", "Bearer ")
                .queryParam("document", "10322308003")
                .when()
                .get("/customer/session")
                .then()
                .statusCode(200)
                .body("data", notNullValue());
    }

    @Test
    @Order(4)
    void mustErroGetClientTest() {
        given()
                .queryParam("document", "10322308004")
                .when()
                .get("/customer")
                .then()
                .statusCode(400)
                .body("error.menssage", equalTo("User not found with the document provided."));
    }
}