package com.example.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Very simple breadcrumb model for beginners.
 */
@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BreadcrumbModel {

   @SlingObject
    private Resource resource;


   @ScriptVariable

   private List<Page> breadcrumpPage= new ArrayList<>();

   @PostConstruct
    public void init(){

       PageManager pageManager = resource.getResourceResolver().adaptTo(PageManager.class);

       Page currentpage = pageManager.getContainingPage(resource);

       while(currentpage!=null && currentpage.getPath().startsWith("/content")) {

           if (!currentpage.isHideInNav()){

               breadcrumpPage.add(currentpage);
           }



           currentpage= currentpage.getParent();

       }

       Collections.reverse(breadcrumpPage);

   }

   public List<Page> getBreadcrumpPage(){
       return breadcrumpPage;
   }

}
