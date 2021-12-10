package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestListOrders {
    @Test
    public void testOrderWithoutKeysReturnListOfOrders()throws Exception{
        Order order = new Order();
        assertEquals(true,order.getOrger().contains("\"orders\""));
        System.err.println(order.getOrderFirstId());
    }
}
