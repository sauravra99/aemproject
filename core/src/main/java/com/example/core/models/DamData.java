package com.example.core.models;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DamData {

    @SlingObject
    private ResourceResolver resolver;

    private String pagePath = "/content/dam/mysite/srv.png";

    private String renditionName;
    private long renditionSize;

    private InputStream assect;

    @PostConstruct
    public void init() {

        Resource resource = resolver.getResource(pagePath);

        if (resource != null) {
            Asset asset = resource.adaptTo(Asset.class);

            if (asset != null) {
                // Read any rendition
                Rendition rendition = asset.getRendition("cq5dam.thumbnail.140.100.png");

                assect = asset.getOriginal().getStream();



                if (rendition != null) {
                    renditionName = rendition.getName();
                    renditionSize = rendition.getSize();


                    InputStream stream = rendition.getStream(); // This gives you the binary
                }
            }
        }
    }

    // ------- Getter Methods --------

    public String getPagePath() {
        return pagePath;
    }

    public String getRenditionName() {
        return renditionName;
    }

    public long getRenditionSize() {
        return renditionSize;
    }

    public InputStream getAssect(){
        return assect;
    }
}
