package com.example.core.models;

import com.example.core.services.ExternalApiServices;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ExternalApiModel {

    @OSGiService
    private ExternalApiServices externalApiServices;


   public String getApiUrl(){
       return externalApiServices.getApiUrl();
   }

   public String getApiKey(){
       return externalApiServices.getApiKey();
   }
}
