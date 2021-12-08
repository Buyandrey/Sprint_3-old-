package com.example;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class testFuncs {
    ArrayList<String> loginPass = new ArrayList<>();
    public String error409Conflict ="{\"code\":400,\"message\":\"Недостаточно данных для создания учетной записи\"}";
    public String code200Ok ="{\"ok\":true}";
    public String error400BadRequest ="{\"code\":400,\"message\":\"Недостаточно данных для входа\"}";
    public String error404NotFounded ="{\"code\":404,\"message\":\"Учетная запись не найдена\"}";
    public String l,p,fn, answerBody;
    String COURIER_PATH="https://qa-scooter.praktikum-services.ru/api/v1/courier";

    public int code;

    public int create(String l, String p, String f) {
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
    public int login(String l, String p){
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
    public String getId(){
        int indexOfDualDots = answerBody.indexOf(':');
        int indexOfCloseBracket = answerBody.indexOf('}');
        System.err.println(answerBody.substring(indexOfDualDots+1,indexOfCloseBracket));
        return answerBody.substring(indexOfDualDots+1,indexOfCloseBracket);
    }
    public int delete(String id){
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("https://qa-scooter.praktikum-services.ru/api/v1/courier/"+id);
        answerBody = response.getBody().asString();
        code = response.statusCode();
        System.err.println(answerBody);
        return  code;
    }
}
