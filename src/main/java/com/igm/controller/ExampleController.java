package com.igm.controller;

import com.igm.configuration.ConfigurationService;
import com.igm.model.ExampleModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExampleController {
    // Default logger
    private final Logger logger = LoggerFactory.getLogger(ExampleController.class);
    private final ConfigurationService configurationService;

    @Autowired
    public ExampleController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }



    @Cacheable("example")
    @GetMapping("/example")
    @ApiOperation(value="returns an string consisting on an object of ExampleModel", response= ExampleModel.class)

    public ExampleModel getAJson() {
        logger.debug("Entering /example");
        return new ExampleModel(configurationService.getExamplePropertyFromApplicationYml());
    }


}
