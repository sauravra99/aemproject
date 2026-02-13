package com.example.core.servlets;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.eclipse.jetty.util.ajax.JSON;
import org.osgi.service.component.annotations.Component;


@Component(service = Servlet.class
    
)

@SlingServletResourceTypes(
    resourceTypes = "/apps/component/form",
    extensions = "text,json",
    selectors="form"
)

public class TestServlet2 extends SlingSafeMethodsServlet {


    @Override
    protected void doGet( SlingHttpServletRequest request,  SlingHttpServletResponse response)
            throws ServletException, IOException {
                
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

               try {

                String name = request.getParameter("name");
                String email = request.getParameter("email");

                objectBuilder.add("name", name);
                objectBuilder.add("email", email);



                response.setContentType("application/json");
                response.getWriter().write(objectBuilder.toString());
            
                
               } catch (Exception e) {
                response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setStatus(500);
               }

            

    }



}
    

