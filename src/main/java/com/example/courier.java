package com.example;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class courier {
    private final scooterRegisterCourier courier;
    private final String courierLogin;
    private final String courierPassword;
    private final String courierFirstName;
    private final String courierId;
    public courier(){
        courier = new scooterRegisterCourier();
        courierLogin = courier.registerNewCourierAndReturnLoginPassword().get(0);
        courierPassword = courier.registerNewCourierAndReturnLoginPassword().get(1);
        courierFirstName = courier.registerNewCourierAndReturnLoginPassword().get(2);
        courierId=getId();
    }
    public String getId(){
        /*получение ID
         * Запрос на логин и потом получение id из ответа
         * */
        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\"}";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        String answerBody=response.
                getBody().
                asString();
        System.err.println("id: "+ answerBody.substring(answerBody.indexOf(':')+1,answerBody.indexOf('}')));
        return answerBody.substring(answerBody.indexOf(':')+1,answerBody.indexOf('}') );
    }

}
