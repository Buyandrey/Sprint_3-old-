package com.example;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestLoginCourier {
// Коды ответов ошибок из api
    public String ERROR_400_BAD_REQUEST = "{\"code\":400,\"message\":\"Недостаточно данных для входа\"}";
    public String ERROR_404_NOT_FOUNDED = "{\"code\":404,\"message\":\"Учетная запись не найдена\"}";
// Создание курьера
    Courier deliveryMan;
// Удаление курьера после каждого теста
    @After
    public void deleteCourier() {
        deliveryMan.delete();
    }
// Тест из задания 2.1, 2.6  курьер может авторизоваться; успешный запрос возвращает id */
    @DisplayName ("Test Login courier and return ID")
    @Test
    public void testSuccessfullyLoginAndReturnId() {
        deliveryMan = new Courier();deliveryMan.register();
        assertEquals("Successfully login", true, deliveryMan.login().contains(deliveryMan.getId()));
    }
// Тест из задания 2.2  для авторизации нужно передать все обязательные поля; */
    @DisplayName ("Test Login courier with correct login and password")
    @Test
    public void testSuccessfullyLoginWithAllNecessaryFields() {
        deliveryMan = new Courier("buyandrey101221","1234","Andrey");deliveryMan.register();
        assertEquals("Successfully login", true, deliveryMan.login().contains(deliveryMan.getId()));
    }
/* Тест из задания 2.4 если какого-то поля нет, запрос возвращает ошибку*/
    @DisplayName ("Test Login courier without login is impossible")
    @Test
    public void testLoginWithoutLogin() throws Exception {
        deliveryMan = new Courier("", RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));deliveryMan.register();
        assertEquals("Login without login", true, deliveryMan.login().equals(ERROR_400_BAD_REQUEST));
    }
/* Тест из задания 2.4 если какого-то поля нет, запрос возвращает ошибку*/
    @DisplayName ("Test Login courier without password is impossible")
    @Test
    public void testLoginWithoutPassword() throws Exception {
        deliveryMan = new Courier(RandomStringUtils.randomAlphabetic(10),
                "", RandomStringUtils.randomAlphabetic(10));deliveryMan.register();
        assertEquals("Login without password", true, deliveryMan.login().equals(ERROR_400_BAD_REQUEST));
    }
/* Тест из задания 2.4 если какого-то поля нет, запрос возвращает ошибку*/
    @DisplayName ("Test Login courier without password and login is impossible")
    @Test
    public void testLoginWithoutLoginAndPassword() throws Exception {
        deliveryMan = new Courier("", "", RandomStringUtils.randomAlphabetic(10));deliveryMan.register();
        assertEquals("Login without login and password", true, deliveryMan.login().equals(ERROR_400_BAD_REQUEST));
    }

/* Тест из задания 2.5 если авторизоваться под несуществующим пользователем, запрос возвращает ошибку */
    @DisplayName ("Test Login courier with unexisted courier is impossible")
    @Test
    public void testLoginWithUnexistedCourier() throws Exception {
        deliveryMan = new Courier();deliveryMan.register();

        assertEquals("Login with unexisted courier", true,
                deliveryMan
                        .login(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10))
                        .equals(ERROR_404_NOT_FOUNDED));
    }

/* Тест из задания 2.3 система вернёт ошибку, если неправильно указать логин или пароль; */
    @DisplayName ("Test Login courier with wrong login is impossible")
    @Test
    public void testLoginWithUncorrectLoginAndCorrectPassword() throws Exception {
        deliveryMan = new Courier();deliveryMan.register();
        assertEquals("Login with wrong login", true, deliveryMan.login(
                deliveryMan.getLogin() + "sdfss", deliveryMan.getPassword())
                .equals(ERROR_404_NOT_FOUNDED));
    }

/* Тест из задания 2.3 система вернёт ошибку, если неправильно указать логин или пароль; */
    @DisplayName ("Test Login courier with wrong password is impossible")
    @Test
    public void testLoginWithUncorrectPasswordAndCorrectlogin() throws Exception {
        deliveryMan = new Courier();deliveryMan.register();
        assertEquals("Login with wrong login", true, deliveryMan.login(
                deliveryMan.getLogin(), deliveryMan.getPassword() + "sdfss")
                .equals(ERROR_404_NOT_FOUNDED));
    }
}
