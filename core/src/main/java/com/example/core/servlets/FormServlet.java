package com.example.core.servlets;

import java.io.IOException;


import javax.servlet.Servlet;
import javax.servlet.ServletException;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component(service = { Servlet.class }, immediate = true)
@SlingServletPaths(value = "/bin/form/submit")

public class FormServlet extends SlingAllMethodsServlet {

     private static final Logger log =LoggerFactory.getLogger(FormServlet.class);

    @Override
    protected void doPost( SlingHttpServletRequest request,  SlingHttpServletResponse response)
            throws ServletException, IOException {

                try {
                    
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");

                    log.info("component {}: ", request.getResource().getResourceType());
                    log.info("name{}, email{}, ",name,email );

                    response.setContentType("application/json");
                    response.getWriter().write(0);

                } 
                
                catch (Exception e) {
                    

                    response.sendError(500,"internal server error");
                }
        
           



    }

}
