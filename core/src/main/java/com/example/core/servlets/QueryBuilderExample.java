package com.example.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service = {Servlet.class}, immediate = true)
@ServiceDescription("Demo query servlet")
@SlingServletResourceTypes(resourceTypes ="/app/test/form", extensions = "json",selectors = "query")

public class QueryBuilderExample extends SlingAllMethodsServlet {
    

    @Override
    protected void doPost( SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

     
                Resource resource = request.getResource();

                ResourceResolver resolver = request.getResourceResolver();

                // Adapt Query builder 

                QueryBuilder queryBuilder = resolver.adaptTo(QueryBuilder.class);

                // creating predicate map

                Map <String , String> map = new HashMap<>();

                map.put("path", "/content/en/us/test");
                map.put("type", "cq:page");
                map.put("p.limit", "5");

                // getting session

                Session session = resolver.adaptTo(Session.class);

                // Creating query

                Query query = queryBuilder.createQuery(PredicateGroup.create(map),session);

                // get result 

                SearchResult result = query.getResult();

                List<Hit>hit = result.getHits();

                response.setContentType("application/text");

                response.getWriter().write("Title from post Servlet: " + resource.getValueMap().get(JcrConstants.JCR_TITLE));








            
                
            
        
    }

}
