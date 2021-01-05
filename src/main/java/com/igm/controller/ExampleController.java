package com.igm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExampleController {
    // Default logger
    private final Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @Cacheable("example")
    @GetMapping("/example")
    public String getAJson() {
        logger.debug("Entering /example");
        return "Example";
    }
}
