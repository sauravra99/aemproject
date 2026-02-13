package com.example.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;

import org.osgi.service.component.annotations.Component;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.search.result.Hit;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/querybuilder-test")
public class QueryBuilderServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response)
            throws ServletException, IOException {        

        ResourceResolver resourceResolver = request.getResourceResolver();

        // Adapt QueryBuilder
        QueryBuilder queryBuilder =
                resourceResolver.adaptTo(QueryBuilder.class);

        // Adapt Session
        Session session = resourceResolver.adaptTo(Session.class);

        // Predicate Map
        Map<String, String> map = new HashMap<>();
        map.put("path", "/content/mysite/us/en/home");
        map.put("type", "cq:Page");
        map.put("p.limit", "-1");
        map.put("p.nodedepth", "1"); 

        // Create Query
        Query query = queryBuilder.createQuery(
                PredicateGroup.create(map),
                session
        );

        SearchResult result = query.getResult();

        //creating array builder to store the paths of the results

       JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        try {
            for (Hit hit : result.getHits()) {

                arrayBuilder.add(hit.getPath());
            }        
        }
        catch (RepositoryException e) {
            response.getWriter().write("{\"error\":\"Repository error\"}");
            return;
        }

        // Create JSON object builder to build the final JSON response

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("path", arrayBuilder);

        response.setContentType("application/json");

        response.getWriter().write(objectBuilder.build().toString());
    }
}
