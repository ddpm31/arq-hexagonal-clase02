package com.example.application;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class OrderServiceTest {

    @Inject
    OrderService orderService;

    @Test
    public void testCreateOrder() {

        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        assertNotNull(order.getId());

    }

    @Test
    public void testAddItemToOrder() {

        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        OrderItem item = new OrderItem("product1", 2, new BigDecimal("50.0"));
        orderService.addItemToOrder(order.getId(), item);
        assertEquals(1, orderService.findOrderById(order.getId()).getItems().size());

    }

    @Test
    public void testUpdateOrderStatus() {

        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        orderService.updateOrderStatus(order.getId(), "CONFIRMED");
        assertEquals("CONFIRMED", orderService.findOrderById(order.getId()).getStatus());

    }

}