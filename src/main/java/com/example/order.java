package com.example;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class order {
    private final String        firstName;
    private final String        lastName;
    private final String        address;
    private final String        metroStation;
    private final String        phone;
                     int        rentTime;
    private final String        deliveryDate;
    private final String        comment;
    private final List<String>  color;
    //RandomStringUtils.randomAlphabetic(10)
    public order(String firstName,
                 String lastName,
                 String address,
                 String metroStation,
                 String phone,
                    int rentTime,
                 String deliveryDate,
                 String comment,
                 List<String> color
    ){

        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.metroStation=metroStation;
        this.phone=phone;
        this.rentTime=rentTime;
        this.deliveryDate=deliveryDate;
        this.comment=comment;
        this.color=color;
    }

    public String createOrder(){
        String colorStr;
        if(color.size()==0) colorStr="";
        else if(color.size()==1) colorStr= "\""+color.get(0)+"\"";
        else colorStr="\"" +
                    color.get(0) +
                    "\","+

                    "\"" +
                    color.get(1) +
                    "\"";

        String registerRequestBody =
        "{"+"\n"
          +"    "+ "\"firstName\":\"" + firstName + "\","+"\n"
          +"    "+ "\"lastName\":\"" + lastName + "\","+"\n"
          +"    "+ "\"address\":\"" + address + "\","+"\n"
          +"    "+ "\"metroStation\":\"" + metroStation + "\","+"\n"
          +"    "+ "\"phone\":\"" + phone + "\","+"\n"
          +"    "+ "\"rentTime\":\"" + rentTime + "\","+"\n"
          +"    "+ "\"deliveryDate\":\"" + deliveryDate + "\","+"\n"
          +"    "+ "\"comment\":\"" + comment + "\","+"\n"
          +"    "+  "\"color \": " +
                "[" +
                colorStr +
                "]" +"\n"+
        "}";
        System.err.println(registerRequestBody);

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/orders");
        System.err.println(response.body().asString());
        return response.body().asString();
    }

}
