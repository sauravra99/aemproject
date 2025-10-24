package com.example.core.models;

import com.day.cq.wcm.models.annotations.injectorspecific.StyleOrValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RegistrationModel {

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String submitLabel;

    public String getHeading() {
        return heading;
    }

    public String getSubmitLabel() {
        return submitLabel;
    }
}
