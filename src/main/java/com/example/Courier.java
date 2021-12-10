package com.example;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.given;

public class Courier {

    private  String courierLogin;
    private  String courierPassword;
    private  String courierFirstName;
    private  String courierId;
//cоздание рэндомного курьера без входных параметров
    public Courier() {

        this.courierLogin       = RandomStringUtils.randomAlphabetic(10);
        this.courierPassword    = RandomStringUtils.randomAlphabetic(10);
        this.courierFirstName   = RandomStringUtils.randomAlphabetic(10);
    }
//cоздание  курьера по входным параметрам
    public Courier(String courierLogin, String courierPassword, String courierFirstName) {
        this.courierLogin     = courierLogin;
        this.courierPassword  = courierPassword;
        this.courierFirstName = courierFirstName;

        //System.err.print("Constructor:\n" + "l: "+ courierLogin + "p: "+ courierPassword + "id: "+ courierId);
    }

    public String getLogin() {
        return courierLogin;
    }

    public String getPassword() {
        return courierPassword;
    }

    public String getId() {
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
        String answerBody = response.
                getBody().
                asString();

        //System.err.println("id: " + answerBody.substring(answerBody.indexOf(':') + 1, answerBody.indexOf('}')));

        return answerBody.substring(answerBody.indexOf(':') + 1, answerBody.indexOf('}'));
    }
// запрос на регистрацию курьера
    public String register() {
        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\","
                + "\"firstName\":\"" + courierFirstName + "\"}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier");

        //System.err.print(response.body().asString());

        return response.body().asString();
    }
//запрос на логин курьера с входными полями
    public String login(String courierLogin, String courierPassword){
        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\"}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        courierId=response.body().asString().substring(response.body().asString().indexOf(':') + 1, response.body().asString().indexOf('}'));
        // System.err.print(response.body().asString());
        //System.err.print(courierId);

        return response.body().asString();
    }
// запрос на логин курьера
    public String login(){
        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\"}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        courierId=response.body().asString().substring(response.body().asString().indexOf(':') + 1, response.body().asString().indexOf('}'));

        //System.err.print(response.body().asString());
        //System.err.print(courierId);

        return response.body().asString();
    }
//запрос на удаление курьера
    public String  delete(){
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("https://qa-scooter.praktikum-services.ru/api/v1/courier/"+getId());

        // System.err.print(response.body().asString());

        return response.body().asString();
    }
}
