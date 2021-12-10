package com.example;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestListOrders {
/*Тест на задание 4.1 Проверь, что в тело ответа возвращается список заказов*/
    @DisplayName("Test Order response contains list of orders")
    @Test
    public void testOrderWithoutKeysReturnListOfOrders()throws Exception{
// Создаю заказ
        Order order = new Order();
// Проверка что тело ответа содержит список заказов.
        assertEquals(true,order.getOrgerList().contains("\"orders\""));
        //System.err.println(order.getOrderFirstId());
    }
}
