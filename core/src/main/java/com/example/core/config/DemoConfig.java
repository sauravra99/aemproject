package com.example.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "email service", description = "this is email service")

public @interface DemoConfig {

    @AttributeDefinition(name = "Smpt host")
    String smptHost();

    @AttributeDefinition(name = "smpt port")
    int smptPort () default 345;

    @AttributeDefinition(name = "username")
    String userNmae();

    @AttributeDefinition(name = "password", type = AttributeType.PASSWORD)
    String Password ();

}
