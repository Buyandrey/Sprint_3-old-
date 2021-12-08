package com.example;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testCreateCourier {
courier deliveryMan;
    String error409Conflict="{\"code\":409,\"message\":\"Этот логин уже используется. Попробуйте другой.\"}";
    String error400ConflictEmpty ="{\"code\":400,\"message\":\"Недостаточно данных для создания учетной записи\"}";
    String code200Ok ="{\"ok\":true}";
    @After
    public void deleteCourier(){
        deliveryMan.delete();
    }
    @Test
    public void testSameCourierCannotCreateTwice(){
        deliveryMan = new courier("NONUNIQUE","NONUNIQUE","NONUNIQUE");
        deliveryMan.register();
        courier deliveryManDoppelganger = new courier("NONUNIQUE","NONUNIQUE","NONUNIQUE");
        deliveryManDoppelganger.register().equals(error409Conflict);
    }
    @Test
    public void testCannotCreateEmptyCourier(){
        deliveryMan = new courier("","","");
        assertEquals("Creation courier without login and password",error400ConflictEmpty,deliveryMan.register());
     }
    @Test
    public void testCannotCreateCourierWithoutLogin(){
        deliveryMan = new courier("",RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        assertEquals("Creation courier without login", error400ConflictEmpty,deliveryMan.register());
    }

    @Test
    public void testCannotCreateCourierWithoutPassword(){
        deliveryMan = new courier(RandomStringUtils.randomAlphabetic(10),"", RandomStringUtils.randomAlphabetic(10));
        assertEquals("Creation courier without password",error400ConflictEmpty,deliveryMan.register());
    }

    @Test
    public void testCorrectCreate(){
        deliveryMan = new courier();
        assertEquals("Correct creation courier",code200Ok,deliveryMan.register());
    }
}
