package com.example.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "External Api ", description = "External Api testing")

public @interface ExternalApiConfig {

    @AttributeDefinition(name = "Api key", description = "place your key her")

    String apiKey() default "";


    @AttributeDefinition(name = "Api url", description = "place your url here")
    String apiUrl() default "";

}
