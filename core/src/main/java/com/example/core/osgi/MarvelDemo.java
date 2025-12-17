package com.example.core.osgi;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)

public class MarvelDemo {

    public static final Logger log = LoggerFactory.getLogger(MarvelDemo.class);

    @Activate
    public void activate (){
    log.info("this is activate");

    }

    @Deactivate
    public void deactivate(){


    }

    @Modified
    public void modified(){

    }

    public String marvelDetail(){
        return "this is marvelDetail";
    }
}
