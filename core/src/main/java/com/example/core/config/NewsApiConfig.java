package com.example.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition (name = "News Api Confrigation", description = "")

public @interface NewsApiConfig {

    @AttributeDefinition(name = "Api Key" , description = "Update the correct api Key")
    String apiKey() default "";

    @AttributeDefinition(name = "Api url", description = "Update the correct api Url")
    String apiUrl() default "";

}
