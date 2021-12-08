package com.example;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class testCreateCourier extends testFuncs{


    @Before
    public void before(){
       l = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем пароль
       p = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя курьера
       fn = RandomStringUtils.randomAlphabetic(10);
       answerBody ="";
       code=0;
    }
    @Test
    public void testCreationCourierIsPossible(){
        create(l,p,fn);
        assertEquals("Creation courier",
                code200Ok,
                answerBody
        );
        assertEquals("Login new courier successfull",200,login(l,p));
    }
     @Test
    public void testSameCourierCannotCreateTwice(){
         assertNotEquals("One user created twice",
                 create(l,p,fn),
                 create(l,p,fn)
         );
     }
     @Test
    public void testCannotCreateEmptyCourier(){
        assertEquals("Creation empty courier",400,create("","",""));
        assertEquals(error409Conflict,answerBody);
     }
     @Test
    public void testCannotCreateCourierWithoutLogin(){
         assertEquals("Creation courier without login",400,create("",p,fn));
         assertEquals(error409Conflict,answerBody);
     }
    @Test
    public void testCannotCreateCourierWithoutPassword(){
        assertEquals("Creation courier without login",400,create(l,"",fn));
        assertEquals(error409Conflict,answerBody);
    }
    @Test
    public void testCorrectCreate(){
        assertEquals("Correct creation courier",201,create(l,p,fn));
    }
}
