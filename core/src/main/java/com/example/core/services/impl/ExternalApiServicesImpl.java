package com.example.core.services.impl;

import com.example.core.config.ExternalApiConfig;
import com.example.core.services.ExternalApiServices;
import org.apache.felix.metatype.OCD;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = ExternalApiServices.class, immediate = true)
@Designate(ocd= ExternalApiConfig.class)

public class ExternalApiServicesImpl implements ExternalApiServices {

    private String apiUrl;
    private String apiKey;

    @Activate
    @Modified

    public void activate(ExternalApiConfig config){
        this.apiKey= config.apiKey();
        this.apiUrl= config.apiUrl();

    }

    @Override
    public String getApiKey(){
        return apiKey;
    }

    @Override
    public String getApiUrl(){
        return apiUrl;
    }


}
