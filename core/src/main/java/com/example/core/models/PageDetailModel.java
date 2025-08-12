package com.example.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class PageDetailModel {

    @ScriptVariable
    private Page currentPage;

    private String title;
    private String description;
    private String lastModified;
    private String lastModifiedBy;


     public String getTitle(){
         return title;
     }

     @PostConstruct
     protected void init(){
         if (currentPage != null){
             this.title=currentPage.getTitle();
             this.description=currentPage.getDescription();
             this.lastModifiedBy=currentPage.getLastModifiedBy();
             Calendar mod = this.currentPage.getLastModified();

             if (mod!=null){
                 this.lastModified=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mod.getTime());
             }
         }
     }

     public String getDescription(){
         return description;
     }


     public String getLastModified(){
         return lastModified;
     }

     public String getLastModifiedBy(){
         return lastModifiedBy;
     }
}
