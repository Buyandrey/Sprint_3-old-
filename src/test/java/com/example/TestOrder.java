package com.example;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/* Тесты из задания 3 */
@RunWith(Parameterized.class)
public class TestOrder {
    private final ArrayList<String> color;

    public TestOrder(ArrayList<String> color){
        this.color=color;
    }
    @Parameterized.Parameters
    public static Object[] getTestData() {
        ArrayList<String> c0=new ArrayList<>(); // Пустая строка, которая будет в теле запроса, задание 3.3
        ArrayList<String> c1b=new ArrayList<>();// Пустая строка, которая будет в теле запроса, задание 3.1
        ArrayList<String> c1g=new ArrayList<>();// Пустая строка, которая будет в теле запроса, задание 3.1
        ArrayList<String> c2=new ArrayList<>(); //  Пустая строка, которая будет в теле запроса, задание 3.2
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
    @DisplayName ("Test orders with different colors or without colors are created correctly")
    @Test
    public void checkColors()throws Exception{
// Создание объекта заказа
        Order order = new Order();
// Получение ответа после отправки запроса на создания заказа
        String response = order.makeOrder(color);
// Проверка что ответ на запрос с тем или иным набором цвета содержит в теле ответа трэкномер
        assertEquals("Orders with different Colors",true,response.contains("track"));
    }
}
