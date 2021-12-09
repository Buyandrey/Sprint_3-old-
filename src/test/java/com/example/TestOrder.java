package com.example;


import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestOrder {
    private final ArrayList<String> color;

    public TestOrder(ArrayList<String> color){
        this.color=color;
    }
    @Parameterized.Parameters
    public static Object[] getTestData() {
        ArrayList<String> c0=new ArrayList<>();
        ArrayList<String> c1b=new ArrayList<>();
        ArrayList<String> c1g=new ArrayList<>();
        ArrayList<String> c2=new ArrayList<>();
        c0.clear();
        c1b.add("BLACK");
        c1g.add("GREY");
        c2.add("BLACK");
        c2.add("GREY");
        return new Object[]{
                c0,
                c1b,
                c1g,
                c2
        };
    }
    @DisplayName ("Chect orders with different colors")
    @Test
    public void checkColors()throws Exception{
        Order order = new Order();
        String response = order.makeOrder(color );
        assertEquals("Orders with different Colors",true,response.contains("track"));
    }
}
