import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class testCreateCourier {
    ArrayList<String> loginPass = new ArrayList<>();
    String err409conflict ="{\"code\":400,\"message\":\"Недостаточно данных для создания учетной записи\"}";
    String code200ok ="{\"ok\":true}";
    String l,p,fn, answerBody;
    int code;

    int create(String l, String p, String f) {
        String registerRequestBody = "{\"login\":\"" + l + "\","
                + "\"password\":\"" + p + "\","
                + "\"firstName\":\"" + f + "\"}";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier");
        answerBody = response.getBody().asString();
        code = response.statusCode();
        System.err.println(answerBody +','+ code);
        return  code;
    }
    int login(String l, String p){
        String registerRequestBody = "{\"login\":\"" + l + "\","
                + "\"password\":\"" + p + "\"}";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        answerBody =response.getBody().asString();
        code =response.statusCode();
        System.err.println(answerBody + code);
        return code;
    }

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
                code200ok,
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
        assertEquals(err409conflict,answerBody);
     }
     @Test
    public void testCannotCreateCourierWithoutLogin(){
         assertEquals("Creation courier without login",400,create("",p,fn));
         assertEquals(err409conflict,answerBody);
     }
    @Test
    public void testCannotCreateCourierWithoutPassword(){
        assertEquals("Creation courier without login",400,create(l,"",fn));
        assertEquals(err409conflict,answerBody);
    }
    @Test
    public void testCorrectCreate(){
        assertEquals("Correct creation courier",201,create(l,p,fn));
    }
}
