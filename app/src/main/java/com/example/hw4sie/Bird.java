package com.example.hw4sie;

import java.security.PublicKey;

public class Bird {

    public String birdName;
    public Integer zipcode;
    public String personName;

    public Bird() {
    }

    //Bird object properties for firebase to use when creating a bird object from main activity
    public Bird(String birdName, Integer zipcode, String personName) {
        this.birdName = birdName;
        this.zipcode = zipcode;
        this.personName = personName;
    }
}
