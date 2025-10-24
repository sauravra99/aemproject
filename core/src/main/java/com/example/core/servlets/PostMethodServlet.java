package com.example.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, immediate = true)
@SlingServletResourceTypes(
        resourceTypes = "mysite/components/registration",
        selectors = "formsubmit",
        extensions = {"json", "txt"}
)
public class PostMethodServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(PostMethodServlet.class);



    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String address = request.getParameter("address");


            log.info("Form received for component: {}", request.getResource().getResourceType());
            log.info("Name: {}, Email: {}, Address: {}", name, email, address);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\", \"message\":\"Form submitted successfully!\"}");

        } catch (Exception e) {
            log.error("Error processing form submission", e);
            response.sendError(500, "Internal Server Error");
        }
    }
}
