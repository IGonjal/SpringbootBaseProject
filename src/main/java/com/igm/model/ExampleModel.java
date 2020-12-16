package com.igm.model;

import org.springframework.beans.factory.annotation.Value;

public class ExampleModel {

    public String valA;

    @Value("${property.example}")
    public String examplePropertyFromApplicationYml;

    public ExampleModel() {
        this.valA = "example1";

    }
}
