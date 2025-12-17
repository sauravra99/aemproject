package com.example.core.servlets;


import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.eclipse.jetty.server.Server;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;

@Component(service = Servlet.class, immediate = true,
property = {"sling.servlet.paths=/bin/dam/details",
        "sling.servlet.methods=GET"})

public class TestServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getParameter("path");

        ResourceResolver resourceResolver = request.getResourceResolver();

         Resource assectResource = resourceResolver.getResource(path);

        Asset asset = assectResource.adaptTo(Asset.class);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        String title = asset.getMetadataValue("dc:title");

        String name = asset.getName();

        String mimeType = asset.getMimeType();

        Rendition rendition = asset.getRendition(path);

        String prop = rendition.getProperties().toString();

        objectBuilder.add("title",title);
        objectBuilder.add("name",name);
        objectBuilder.add("mimeType",mimeType);
        objectBuilder.add("rend",prop);

        response.setContentType("application/json");

        response.getWriter().write(objectBuilder.build().toString());




    }
}
