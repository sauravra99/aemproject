package com.example.core.services.impl;

import com.example.core.config.DemoConfig;
import com.example.core.config.NewsApiConfig;
import com.example.core.services.Demoservice;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import java.security.PrivateKey;

@Component(service = Demoservice.class, immediate = true)
@Designate(ocd = DemoConfig.class)

public class DemoServiceImpl implements Demoservice {

    private int port;
    private String username;
    private String password;

    @Modified
    @Activate
    public void activate (DemoConfig config){

        this.port =config.smptPort();
        this.username=config.userNmae();
        this.password=config.Password();

    }


    @Override
    public boolean sendEmail(String to, String subject, String body) {
        try {

        } catch (Exception e) {


        }
        return false;
    }
}
