package com.example.core.models;

import com.example.core.services.WeatherService;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(
        adaptables = org.apache.sling.api.resource.Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class WeatherModel {

    private static final Logger log = LoggerFactory.getLogger(WeatherModel.class);

    @OSGiService
    private WeatherService weatherService;

    @ValueMapValue
    private String city;

    private String temperature;
    private String condition;
    private String humidity;
    private String iconUrl;
    private String localtime;

    @PostConstruct
    protected void init() {
        if (city == null || city.isEmpty()) {
            city = "Delhi"; // default fallback
        }
        String weatherJson = weatherService.getWeather(city);

        try {
            JSONObject json = new JSONObject(weatherJson);

            JSONObject current = json.getJSONObject("current");
            JSONObject location = json.getJSONObject("location");

            this.temperature = String.valueOf(current.getDouble("temp_c"));
            this.humidity = String.valueOf(current.getInt("humidity"));
            this.condition = current.getJSONObject("condition").getString("text");
            this.iconUrl = "https:" + current.getJSONObject("condition").getString("icon");
            this.localtime = location.getString("localtime");

        } catch (Exception e) {
            log.error("Error parsing WeatherAPI.com JSON", e);
        }
    }

    public String getCity() { return city; }
    public String getTemperature() { return temperature; }
    public String getCondition() { return condition; }
    public String getHumidity() { return humidity; }
    public String getIconUrl() { return iconUrl; }
    public String getLocaltime() { return localtime; }
}
