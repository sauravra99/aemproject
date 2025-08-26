package com.example.core.services.impl;

import com.example.core.services.WeatherService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@Component(service = WeatherService.class, immediate = true)
@Designate(ocd = WeatherServiceImpl.Config.class)
public class WeatherServiceImpl implements WeatherService {

    private static final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @ObjectClassDefinition(name = "Weather Service Configuration")
    public @interface Config {
        @AttributeDefinition(name = "API Key", description = "WeatherAPI.com Key")
        String apiKey() default " ";

        @AttributeDefinition(name = "Base URL", description = "Weather API Base URL")
        String baseUrl() default "http://api.weatherapi.com/v1/current.json";
    }

    private String apiKey;
    private String baseUrl;

    @Activate
    @Modified
    protected void activate(Config config) {
        this.apiKey = config.apiKey();
        this.baseUrl = config.baseUrl();
    }

    @Override
    public String getWeather(String city) {
        // Corrected URL for WeatherAPI.com
        String url = String.format("%s?key=%s&q=%s&aqi=no", baseUrl, apiKey, city);

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(new HttpGet(url))) {

            if (response.getStatusLine().getStatusCode() == 200) {
                return IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            } else {
                log.error("Weather API error: {}", response.getStatusLine());
                return "{}";
            }

        } catch (Exception e) {
            log.error("Error calling Weather API", e);
            return "{}";
        }
    }
}
