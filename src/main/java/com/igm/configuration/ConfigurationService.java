package com.igm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationService {

    @Value("${property.example}")
    private String examplePropertyFromApplicationYml;

    public String getExamplePropertyFromApplicationYml() {
        return examplePropertyFromApplicationYml;
    }

    public void setExamplePropertyFromApplicationYml(String examplePropertyFromApplicationYml) {
        this.examplePropertyFromApplicationYml = examplePropertyFromApplicationYml;
    }
}
