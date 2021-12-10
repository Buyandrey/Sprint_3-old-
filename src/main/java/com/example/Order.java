package com.example;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Order {
    //должен просто отправлять запрос на создание ордера через метод
    public String makeOrder(ArrayList<String> color)
            throws Exception{
        String colorStringInBody;
        if(color.size()==0){
            colorStringInBody="\"\"";


        }else if(color.size()==1){
            colorStringInBody =
                    "\""
                    + color.get(0) +
                    "\"";
        }else if(color.size()==2){
            colorStringInBody =
                    "\""
                    + color.get(0) +
                    "\","
                    +
                    "\""
                    + color.get(1) +
                    "\"";
        }else{
            throw new Exception("More than 2 colors");
        }
        //System.err.println(colorStringInBody);
        String registerRequestBody =
        "{"
                + "\"firstName\":\"" + "firstName" + "\","
                + "\"lastName\":\"" + "lastName" + "\","
                + "\"address\":\"" + "address" + "\","
                + "\"metroStation\":\"" + "3" + "\","
                + "\"phone\":\"" + "7 800 355 35 35" + "\","
                + "\"rentTime\":\"" + 5 + "\","
                + "\"deliveryDate\":\"" + "2020-06-06" + "\","
                + "\"comment\":\"" + "comment" + "\","
                + "\"color\":[" + colorStringInBody +"]" +
        "}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/orders");

        //System.err.print(response.body().asString());

        return response.body().asString();
    }
    public String getOrgerList()
            //Проверь, что в тело ответа возвращается список заказов.
            throws Exception{
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders");
        //System.err.println(response.body().asString());
        return response.body().asString();
    }
    public String getOrderFirstId(){
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders");

        int iId= response.body().asString().indexOf("id");
        int firstCommaId= response.body().asString().indexOf(",");
        //System.err.println(response.body().asString().substring(iId+3,firstCommaId));

        return response.body().asString().substring(iId+4,firstCommaId);
    }
}
