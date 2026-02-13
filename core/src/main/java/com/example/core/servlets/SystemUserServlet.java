package com.example.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = Servlet.class)

public class SystemUserServlet extends SlingAllMethodsServlet {

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    protected void doGet( SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        
                Map<String, Object> params =new HashMap<>();
                params.put(ResourceResolverFactory.SUBSERVICE, "myservice");

                
                try (ResourceResolver resourceresolver =resolverFactory.getServiceResourceResolver(params)) {
                    
                   String User_id =  resourceresolver.getUserID();

                } catch (Exception e) {

                  
                    
                }

    }


    
}
