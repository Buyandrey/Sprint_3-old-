package com.example;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class testCreateOrder {
    @Test
    public void testCreationOrder() {
        List<String> color= new ArrayList<>();
        color.add("BLACK");
        color.add("WHITE");
        order order = new order(
                "Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                "4",
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Saske, come back to Konoha",
                color);
        assertEquals("Order created correctly",
                true,
                order.createOrder().contains("track"));
    }
}
