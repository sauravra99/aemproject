package com.example.core.models;


import com.example.core.services.Demoservice;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name = "nested", extensions = "json")

public class TestModel {


    @OSGiService
    private Demoservice demoservice;



    @ChildResource
    private List<ItemModel>items;

    @PostConstruct
    public void init(){

        demoservice.sendEmail("saurav", "test", "this is an email");



    }

    private List<ItemModel> getItems(){
        return items;
    }
    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class ItemModel {

     @ValueMapValue
        private String title;

     @ChildResource
     private List<ImageModel>image;

     public String getTitle(){
         return title;
     }
     private List<ImageModel> getImage () {

         return image;
        }
    }
    public static class ImageModel{

        @ValueMapValue
        public String img;

        public String getImg(){
            return img;
        }

    }


}
