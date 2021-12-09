package com.example;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class testLoginCourier {

    public String error400BadRequest = "{\"code\":400,\"message\":\"Недостаточно данных для входа\"}";
    public String error404NotFounded = "{\"code\":404,\"message\":\"Учетная запись не найдена\"}";
    Courier deliveryMan;

    @After
    public void deleteCourier() {
        deliveryMan.delete();
    }

    @Test
    public void testSuccessfullyLoginWithAllNecessaryFieldsRwturnId() {
        deliveryMan = new Courier();deliveryMan.register();
        assertEquals("Successfully login", true, deliveryMan.login().contains(deliveryMan.getId()));
    }

    @Test
    public void testLoginWithoutLogin() throws Exception {
        deliveryMan = new Courier("", RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));deliveryMan.register();
        assertEquals("Login without login", true, deliveryMan.login().equals(error400BadRequest));
    }

    @Test
    public void testLoginWithoutPassword() throws Exception {
        deliveryMan = new Courier(RandomStringUtils.randomAlphabetic(10), "", RandomStringUtils.randomAlphabetic(10));deliveryMan.register();
        //deliveryMan.setEmptyPassword();
        assertEquals("Login without password", true, deliveryMan.login().equals(error400BadRequest));
    }

    @Test
    public void testLoginWithoutLoginAndPassword() throws Exception {
        //deliveryMan.setEmptyPassword();
        //deliveryMan.setEmptyLogin();
        deliveryMan = new Courier("", "", RandomStringUtils.randomAlphabetic(10));deliveryMan.register();
        assertEquals("Login without login and password", true, deliveryMan.login().equals(error400BadRequest));
    }

    @Test
    public void testLoginWithUnexistedCourier() throws Exception {
        deliveryMan = new Courier();deliveryMan.register();

        assertEquals("Login with unexisted courier", true,
                deliveryMan
                        .login(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10))
                        .equals(error404NotFounded));
    }

    @Test
    public void testLoginWithUncorrectLoginAndCorrectLogin() throws Exception {
        deliveryMan = new Courier();deliveryMan.register();
        assertEquals("Login with wrong login", true, deliveryMan.login(
                deliveryMan.getLogin() + "sdfss", deliveryMan.getPassword())
                .equals(error404NotFounded));
    }

    @Test
    public void testLoginWithUncorrectPasswordAndCorrectlogin() throws Exception {
        deliveryMan = new Courier();deliveryMan.register();
        assertEquals("Login with wrong login", true, deliveryMan.login(
                deliveryMan.getLogin(), deliveryMan.getPassword() + "sdfss")
                .equals(error404NotFounded));

    }
}
