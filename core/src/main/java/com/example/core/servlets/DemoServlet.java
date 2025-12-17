package com.example.core.servlets;

import jdk.nashorn.internal.runtime.Property;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;


@Component(service = Servlet.class, immediate = true)

@SlingServletResourceTypes(resourceTypes = "mysite/components/weather",
                            selectors={"add","sub","mul"},
                            extensions = {"json","txt"})

public class DemoServlet extends SlingSafeMethodsServlet  {


    String pagePath ="/content/mysite/us/en/home";
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(pagePath);

        Resource child = resource.getChild("jcr:content");

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        if (child!=null){
            ValueMap valueMap = child.getValueMap();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            String title = valueMap.get("jcr:title",String.class);
            String resourceType =valueMap.get("sling:resourceType",String.class);

            if (title!=null & resourceType!=null) {
                objectBuilder.add("jcr:title", title);
                objectBuilder.add("sling:resourceType", resourceType);
            }
            arrayBuilder.add(objectBuilder);

        }
        response.getWriter().write(arrayBuilder.build().toString());


    }
}
