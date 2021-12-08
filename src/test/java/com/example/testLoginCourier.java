package com.example;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static org.junit.Assert.assertEquals;

public class testLoginCourier extends testFuncs{
    int count=0;
    public String lFixed ="buyandrey96", pFixed ="1234", fnFixed ="andrey",idFixed="11134";
    @Test
    public void testSuccessfullyLoginWithAllNecessaryFields() {
        assertEquals("Successfully login",200,login(lFixed, pFixed));
    }
    @Test
    public void testLoginWithoutLogin() throws Exception{
        assertEquals("Login without login",400,login("", pFixed));
        assertEquals("Login without login",error400BadRequest,answerBody);
    }
    @Test
    public void testLoginWithoutPassword() throws Exception{
        assertEquals("Login without password",400,login(lFixed,""));
        assertEquals("Login without password",error400BadRequest,answerBody);
    }
    @Test
    public void testLoginWithUnexistedCourier() throws Exception{
        assertEquals("Login Unexisted Courier",404,login("HiIamUnexistedCouriersidhfvlisdfvbolfvh","1234"));
        assertEquals("Login Unexisted Courier",error404NotFounded,answerBody);
    }
    @Test
    public void testLoginWithUncorrectLoginAndCorrectLogin() throws Exception{
        assertEquals("Login with wrong login",404,login(lFixed +"667", pFixed));
        assertEquals("Login with wrong login",error404NotFounded,answerBody);
    }
    @Test
    public void testLoginWithUncorrectPasswordAndCorrectlogin() throws Exception{
        assertEquals("Login with wrong password",404,login(lFixed, pFixed +"667"));
        assertEquals("Login with wrong password",error404NotFounded,answerBody);
    }
    @Test
    public void  testIdIsOkWhenLoginWasCorrect(){
        assertEquals("Successfully login",200,login(lFixed, pFixed));
        assertEquals("Successfully login and id OK",true,answerBody.contains(idFixed));
    }
}
