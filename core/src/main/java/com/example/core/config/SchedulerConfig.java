package com.example.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Scheduler testing")
public @interface SchedulerConfig {

    @AttributeDefinition(name = "Enter the name of sechiduler", description = "Demo")

     String name() default "Scheduler test";

    @AttributeDefinition(name = "Enter the expression" , description = "cron expression")

    String schedulerExpression() default "0 * * * * ?";


}
