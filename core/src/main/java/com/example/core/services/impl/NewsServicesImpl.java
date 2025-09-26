package com.example.core.services.impl;

import com.example.core.config.NewsApiConfig;
import com.example.core.services.NewsServices;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;


@Component(service = NewsServices.class, immediate = true)
@Designate(ocd = NewsApiConfig.class)

public class NewsServicesImpl implements NewsServices {

    private String apiKey;
    private String apiUrl;

    @Activate
    @Modified

    public void activate (NewsApiConfig config){

        this.apiKey= config.apiKey();
        this.apiUrl= config.apiUrl();
    }


    @Override
    public String getTopHeadline (String country,String category) {
        try {
            String endpoint = String.format("%s?country=%s&category=%s&apiKey=%s", country,category,apiKey,apiUrl);

            Content response = Request.Get(endpoint)
                    .connectTimeout(5000)
                    .socketTimeout(5000)
                    .execute()
                    .returnContent();


            return response.asString();

        }
        catch (Exception e){
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }



}
