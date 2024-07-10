package com.example.application.rest;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class OrderResourceTest {

    @Test
    public void testCreateOrderEndpoint() {

        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
            .contentType("application/json")
            .body(order)
            .when().post("/orders")
            .then()
                .statusCode(201)
                .body("status", is("PENDING"));

    }

    @Test
    public void testAddItemToOrderEndpoint() {

        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                    .statusCode(201);

        OrderItem item = new OrderItem("product1", 2, new BigDecimal("50.0"));
        given()
                .contentType("application/json")
                .body(item)
                .when().post("/orders/{orderId}/items")
                .then()
                    .statusCode(201);

    }

    @Test
    public void testUpdateOrderStatusEndpoint() {

        String s = "CONFIRMED";
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                    .statusCode(201);
        given()
            .contentType("application/json")
            .body(s)
            .when().put("/orders/{orderId}/items", order.getId())
            .then()
                .statusCode(201)
                .body("status", is("CONFIRMED"));

    }





}