package com.example.core.servlets;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;


import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {Servlet.class}, immediate = true,
          property = {
                  "sling.servlet.paths=/bin/test/servlet",
                  "sling.servlet.methods=GET"
})



@SlingServletResourceTypes(
        resourceTypes = "mysite/components/title",
        selectors = {"add", "sub", "mul"},
        extensions = {"json", "html", "txt"}
)


public class ResourceBasedServlet extends SlingSafeMethodsServlet {

    String pagePath ;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        this.pagePath=request.getParameter("pagePath");

        ResourceResolver resourceResolver = request.getResourceResolver();

        Resource resource = resourceResolver.getResource(pagePath);
        Resource contentResource = resource.getChild("jcr:content");
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        if (contentResource!=null){

            ValueMap property = contentResource.adaptTo(ValueMap.class);

            if (property !=null){

                String title = property.get("jcr:title", String.class);
                String resourceType= property.get("sling:resourceType", String.class);
                String created = property.get("jcr:created", String.class);

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

                if (title!=null || resourceType !=null || created !=null){

                    objectBuilder.add("title",title);
                    objectBuilder.add("resourceType", resourceType);
                    objectBuilder.add("created",created);

                    arrayBuilder.add(objectBuilder);
                }
                response.setContentType("application/json");
                response.getWriter().write(arrayBuilder.build().toString());
            }




        }


    }
}

