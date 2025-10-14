package com.example.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(
        service = { Servlet.class },
        immediate = true,
        // register as 1.2 v as a path
        property = {
                "Sling.Servlet.path= /demo/test/servlet",
                "Sling.servlet.Method= GET"
        }

)
 //register in 1.4 as a path
@SlingServletPaths(value = "/demo/test/servlet")

public class DemoServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("path based servlet", "path based servlet built on 1.2v");
        job.add("mysite", "mysite.test.com");


        response.setContentType("application/Json");
        response.getWriter().write(job.build().toString());
    }
}
