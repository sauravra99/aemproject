package com.example.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

@Component(service = {Servlet.class}, immediate = true)
@SlingServletPaths(value = "/bin/systeminfo")
public class SystemInfoServlet extends SlingAllMethodsServlet{

   private static final Logger log=  LoggerFactory.getLogger(SystemInfoServlet.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request,  SlingHttpServletResponse response)
            throws ServletException, IOException {

                Map <String , Object > params = new HashMap<>();

                params.put(ResourceResolverFactory.SUBSERVICE, "myServiceUser");

                try (ResourceResolver resourceResolver = resolverFactory.getServiceResourceResolver(params)){

                    JsonObject json = new JsonObject();

                    String javaVersion = System.getProperty("java.version");
                    String osName = System.getProperty("os.name");

                    json.addProperty("osName", osName);
                    json.addProperty("java version", javaVersion);

                 
                    Resource content = resourceResolver.getResource("/content/mysite/us/en");

                    if (content != null){

                        json.addProperty("contentExist", true);
                    }

                    else{
                        json.addProperty("contentExist", false);
                    }

                    response.setContentType("application/json");
                    response.getWriter().write(json.toString());

                    log.info("Service user id {} : ", resourceResolver.getUserID());
                     
                    

                } catch (Exception e) {
                    log.error("LoginException: Failed to get service resolver", e);
                    response.setStatus(500);
                    response.getWriter().write("{\"error\":\"LoginException: " + e.getMessage() + "\"}");
                }

            
        


    }
    
}
