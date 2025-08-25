package com.example.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;



@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class LayoutModel {

    @ValueMapValue
    private String layoutType;

    public String getLayoutType(){
        return layoutType != null ? layoutType : "two";
    }

    public boolean isTwoColumn() {
        return "two".equals(getLayoutType());
    }

    public boolean isThreeColumn() {
        return "three".equals(getLayoutType());
    }

}
