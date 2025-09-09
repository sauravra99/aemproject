package com.example.core.models;

import com.example.core.utils.PageUtilsUrl;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class CardModel {

    @ValueMapValue
    private String fileReference;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private boolean openInNewTab;


    public String getFileReference() {
        return fileReference;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return PageUtilsUrl.getValidUrl(link);
    }

    public boolean isOpenInNewTab() {
        return openInNewTab;
    }

}
