package com.example.core.models;

import com.example.core.services.StudentServices;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(
        adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class StudentModel {

    @OSGiService
    private StudentServices studentService;

    public String getName() {
        return studentService != null ? studentService.getName() : "";
    }

    public int getRoll() {
        return studentService != null ? studentService.getRoll() : 0;
    }

    public String[] getCountries() {
        return studentService != null ? studentService.getCountries() : new String[]{};
    }

    public String[] getSubject() {
        return studentService != null ? studentService.getSubject() : new String[]{};
    }

    public boolean getRegular() {
        return studentService != null && studentService.getRegular();
    }
}
