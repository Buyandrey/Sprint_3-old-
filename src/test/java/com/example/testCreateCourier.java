package com.example;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testCreateCourier {
Courier deliveryMan;
    String error409Conflict="{\"code\":409,\"message\":\"Этот логин уже используется. Попробуйте другой.\"}";
    String error400ConflictEmpty ="{\"code\":400,\"message\":\"Недостаточно данных для создания учетной записи\"}";
    String code200Ok ="{\"ok\":true}";
    @After
    public void deleteCourier(){
        deliveryMan.delete();
    }
    @Test
    public void testSameCourierCannotCreateTwice(){
        deliveryMan = new Courier("NONUNIQUE","NONUNIQUE","NONUNIQUE");
        deliveryMan.register();
        Courier deliveryManDoppelganger = new Courier("NONUNIQUE","NONUNIQUE","NONUNIQUE");
        deliveryManDoppelganger.register().equals(error409Conflict);
    }
    @Test
    public void testCannotCreateEmptyCourier(){
        deliveryMan = new Courier("","","");
        assertEquals("Creation courier without login and password",error400ConflictEmpty,deliveryMan.register());
     }
    @Test
    public void testCannotCreateCourierWithoutLogin(){
        deliveryMan = new Courier("",RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        assertEquals("Creation courier without login", error400ConflictEmpty,deliveryMan.register());
    }

    @Test
    public void testCannotCreateCourierWithoutPassword(){
        deliveryMan = new Courier(RandomStringUtils.randomAlphabetic(10),"", RandomStringUtils.randomAlphabetic(10));
        assertEquals("Creation courier without password",error400ConflictEmpty,deliveryMan.register());
    }

    @Test
    public void testCorrectCreate(){
        deliveryMan = new Courier();
        assertEquals("Correct creation courier",code200Ok,deliveryMan.register());
    }
}
