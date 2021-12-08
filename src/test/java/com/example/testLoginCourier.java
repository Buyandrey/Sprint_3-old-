package com.example;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class testLoginCourier extends testFuncs{
    courier deliveryMan;
    @After
    public void deleteCourier(){
        code=0;
        answerBody="";
        deliveryMan.delete();
    }
    @Test
    public void testSuccessfullyLoginWithAllNecessaryFields() {
        deliveryMan = new courier();
        assertEquals("Successfully login",true,deliveryMan.login().contains("id"));
    }
    @Test
    public void testLoginWithoutLogin() throws Exception{
        deliveryMan= new courier("","1234","1245");
        assertEquals("Login without login",true,deliveryMan.login().equals(error400BadRequest));
    }
    @Test
    public void testLoginWithoutPassword() throws Exception{
        deliveryMan= new courier("641fgadfg3416","","1245");
        //deliveryMan.setEmptyPassword();
        assertEquals("Login without password",true,deliveryMan.login().equals(error400BadRequest));
    }
    @Test
    public void testLoginWithoutLoginAndPassword() throws Exception{
        //deliveryMan.setEmptyPassword();
        //deliveryMan.setEmptyLogin();
        deliveryMan= new courier("","","2134");
        assertEquals("Login without login and password",true,deliveryMan.login().equals(error400BadRequest));
    }
    @Test
    public void testLoginWithUnexistedCourier() throws Exception{
        deliveryMan = new courier();

        assertEquals("Login with unexisted courier",true,
                deliveryMan
                        .login("80-ew7pryhf;rthertvnuidshf","4898+471658457yt-phb2")
                        .equals(error404NotFounded));
    }
    @Test
    public void testLoginWithUncorrectLoginAndCorrectLogin() throws Exception{
        deliveryMan= new courier();
        assertEquals("Login with wrong login",true,deliveryMan.login(
                deliveryMan.getLogin()+"sdfss", deliveryMan.getPassword())
                .equals(error404NotFounded));
    }
    @Test
    public void testLoginWithUncorrectPasswordAndCorrectlogin() throws Exception{
        deliveryMan= new courier();
        assertEquals("Login with wrong login",true,deliveryMan.login(
                deliveryMan.getLogin(), deliveryMan.getPassword()+"sdfss")
                .equals(error404NotFounded));

    }
    @Test
    public void testIdIsOkWhenLoginWasCorrect(){
        deliveryMan= new courier();
        assertEquals("Successfully login",true,deliveryMan.login().contains(deliveryMan.getId()));
    }

}
