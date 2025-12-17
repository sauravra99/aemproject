package com.example.core.osgi;

import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)

public class Demo {

    public static final Logger log = LoggerFactory.getLogger(Demo.class);

    @Reference
    private MarvelDemo marvelDemo;


    @Activate
    public void activate (){
        String marveldetail = marvelDemo.marvelDetail();
        log.info("this is activate event ");
        log.info("this is marvel detail - {}", marveldetail);

    }

    @Deactivate
    public void deactivate(){

        log.info("this is deactivate event ");
    }

    @Modified
    public void modified(){

    }
}
