package com.example.core.services.impl;

import com.example.core.config.StudentConfig;
import com.example.core.services.StudentServices;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = StudentServices.class, immediate = true)
@Designate(ocd = StudentConfig.class)
public class StudentServiceImpl implements StudentServices {

    private StudentConfig config;

    @Activate
    @Modified
    protected void activate(StudentConfig config) {
        this.config = config;
    }

    @Override
    public String getName() { return config.name(); }

    @Override
    public int getRoll() { return config.roll(); }

    @Override
    public String[] getSubject() { return config.subject(); }

    @Override
    public boolean getRegular() { return config.regular(); }

    @Override
    public String[] getCountries() { return config.countries(); }
}
