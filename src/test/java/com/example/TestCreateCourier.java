package com.example;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCreateCourier {
Courier deliveryMan;
    String error409Conflict="{\"code\":409,\"message\":\"Этот логин уже используется. Попробуйте другой.\"}";
    String error400ConflictEmpty ="{\"code\":400,\"message\":\"Недостаточно данных для создания учетной записи\"}";
    String code200Ok ="{\"ok\":true}";
    @After
    public void deleteCourier(){
        deliveryMan.delete();
    }
    @DisplayName("Test the same courier can't be created twice")
    @Test
    public void testSameCourierCannotCreateTwice(){
        deliveryMan = new Courier("NONUNIQUE","NONUNIQUE","NONUNIQUE");
        deliveryMan.register();
        Courier deliveryManDoppelganger = new Courier("NONUNIQUE","NONUNIQUE","NONUNIQUE");
        deliveryManDoppelganger.register().equals(error409Conflict);
    }
    @DisplayName("Test create empty courier is impossible")
    @Test
    public void testCannotCreateEmptyCourier(){
        deliveryMan = new Courier("","","");
        assertEquals("Creation courier without login and password",error400ConflictEmpty,deliveryMan.register());
     }
    @DisplayName("Test create courier without login is impossible")
    @Test
    public void testCannotCreateCourierWithoutLogin(){
        deliveryMan = new Courier("",RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        assertEquals("Creation courier without login", error400ConflictEmpty,deliveryMan.register());
    }
    @DisplayName("Test create courier without password is impossible")
    @Test
    public void testCannotCreateCourierWithoutPassword(){
        deliveryMan = new Courier(RandomStringUtils.randomAlphabetic(10),"", RandomStringUtils.randomAlphabetic(10));
        assertEquals("Creation courier without password",error400ConflictEmpty,deliveryMan.register());
    }
    @DisplayName("Test correct creation")
    @Test
    public void testCorrectCreate(){
        deliveryMan = new Courier();
        assertEquals("Correct creation courier",code200Ok,deliveryMan.register());
    }
}
