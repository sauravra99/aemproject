package com.example.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Student configuration", description = "Student detail")
public @interface StudentConfig {

    @AttributeDefinition(name = "Name", description = "Enter your name")
    String name() default "Saurav";

    @AttributeDefinition(name = "Roll", description = "Enter your roll number")
    int roll() default 12;

    @AttributeDefinition(name = "Subject", description = "Enter your subject")
    String[] subject() default {"Science", "Math"};

    @AttributeDefinition(name = "Regular", description = "Is Regular")
    boolean regular() default false;

    @AttributeDefinition(
            name = "Countries",
            description = "Select your countries",
            options = {
                    @Option(label = "India", value = "India"),
                    @Option(label = "US", value = "US"),
                    @Option(label = "Canada", value = "Canada")
            }
    )
    String[] countries() default {"India"};
}















