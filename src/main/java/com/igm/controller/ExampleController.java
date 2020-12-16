package com.igm.controller;

import com.google.gson.Gson;
import com.igm.model.ExampleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExampleController {
    Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @Autowired
    private ExampleModel anAutowiredExample;

    @GetMapping("/example")
    public String getAJson() {
        logger.debug("Entering /example");
        return new Gson().toJson(anAutowiredExample);
    }
}
