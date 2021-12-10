package com.example;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCreateCourier {
// Создание курьера
    Courier deliveryMan;
// Тексты ответа на запрос
    String error409Conflict="{\"code\":409,\"message\":\"Этот логин уже используется. Попробуйте другой.\"}";
    String error400ConflictEmpty ="{\"code\":400,\"message\":\"Недостаточно данных для создания учетной записи\"}";
    String code200Ok ="{\"ok\":true}";

// Удаление курьера после каждого теста
    @After
    public void deleteCourier(){
        deliveryMan.delete();
    }
// Тест на задание 1.2 нельзя создать двух одинаковых курьеров
    @DisplayName("Test the same courier can't be created twice")
    @Test
    public void testSameCourierCannotCreateTwice(){
// Создание курьера через конструктор курьера
        deliveryMan = new Courier("NONUNIQUE","NONUNIQUE","NONUNIQUE");
// Регистрация курьера
        deliveryMan.register();
// Создание второго курьера через конструктор с теми же полями что и у первого
        Courier deliveryManDoppelganger = new Courier("NONUNIQUE","NONUNIQUE","NONUNIQUE");
// Ожидание ошибки 409 при регистрации двойника
        assertEquals("Test the same courier can't be created twice",error409Conflict,deliveryManDoppelganger.register());
    }
// Тест из задания 1.3 чтобы создать курьера, нужно передать в ручку все обязательные поля
    @DisplayName("Test create empty courier is impossible")
    @Test
    public void testCannotCreateEmptyCourier(){
// Создание курьера с пустыми полями через конструктор курьера
        deliveryMan = new Courier("","","");
// Ожидание кода ошибки 400
        assertEquals("Creation courier without login and password",error400ConflictEmpty,deliveryMan.register());
     }
/* Тест из задания 1.6 если одного из полей нет, запрос возвращает ошибку */
    @DisplayName("Test create courier without login is impossible")
    @Test
    public void testCannotCreateCourierWithoutLogin(){
// Создание курьера с пустым полем логина через конструктор курьера
        deliveryMan = new Courier("",RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
// Ожидание кода ошибки 400
        assertEquals("Creation courier without login", error400ConflictEmpty,deliveryMan.register());
    }
/* Тест из задания 1.6 если одного из полей нет, запрос возвращает ошибку*/
    @DisplayName("Test create courier without password is impossible")
    @Test
    public void testCannotCreateCourierWithoutPassword(){
// Создание курьера с пустым полем пароль через конструктор курьера
        deliveryMan = new Courier(RandomStringUtils.randomAlphabetic(10),"", RandomStringUtils.randomAlphabetic(10));
// Ожидание кода ошибки 400
        assertEquals("Creation courier without password",error400ConflictEmpty,deliveryMan.register());
    }
/* Тест из задания 1.1, 1.3 ,1.5 курьера можно создать; чтобы создать курьера, нужно передать в ручку все обязательные поля; успешный запрос возвращает ok: true */
    @DisplayName("Test correct creation")
    @Test
    public void testCorrectCreate(){
// Создание курьера с заполненными обязательными полями
        deliveryMan = new Courier(RandomStringUtils.randomAlphabetic(10),RandomStringUtils.randomAlphabetic(10),RandomStringUtils.randomAlphabetic(10));
// Ожидание кода 200
        assertEquals("Correct creation courier",code200Ok,deliveryMan.register());
    }
}
