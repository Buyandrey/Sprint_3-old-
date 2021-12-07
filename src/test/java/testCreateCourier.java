import com.example.scooterRegisterCourier;
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
    String l,p,fn,hint;

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
        hint = response.getBody().asString();
        System.err.println(hint);
        return response.statusCode();
    }

    @Before
    public void before(){
       l = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем пароль
         p = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя курьера
         fn = RandomStringUtils.randomAlphabetic(10);
    }
     @Test
    public void testOneCourierCannotCreateTwice(){


         assertNotEquals("One user created twice",
                 create(l,p,fn),
                 create(l,p,fn)
         );
     }
     @Test
    public void testCannotCreateEmptyCourier(){
        assertEquals("Creation empty courier",400,create("","",""));
    }
     @Test
    public void testCannotCreateCourierWithoutLogin(){
         String l = RandomStringUtils.randomAlphabetic(10);
         // с помощью библиотеки RandomStringUtils генерируем пароль
         String p = RandomStringUtils.randomAlphabetic(10);
         // с помощью библиотеки RandomStringUtils генерируем имя курьера
         String fn = RandomStringUtils.randomAlphabetic(10);
         //System.out.println(create("",p,fn));
         assertEquals("Creation courier without login",400,create("",p,fn));

     }
    @Test
    public void testCannotCreateCourierWithoutPassword(){
        String l = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем пароль
        String p = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя курьера
        String fn = RandomStringUtils.randomAlphabetic(10);
        //System.out.println(create("",p,fn));
        assertEquals("Creation courier without login",400,create(l,"",fn));

    }
    @Test
    public void testCorrectCreate(){
        String l = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем пароль
        String p = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя курьера
        String fn = RandomStringUtils.randomAlphabetic(10);
        //System.out.println(create(l,p,fn));
        assertEquals("Correct creation courier",201,create(l,p,fn));
        assertEquals("{\"ok\":true}",hint);
    }
}
