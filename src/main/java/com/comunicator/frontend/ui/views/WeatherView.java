package com.comunicator.frontend.ui.views;

import com.comunicator.frontend.data.InfoLogToCreate;
import com.comunicator.frontend.data.LocData;
import com.comunicator.frontend.data.WeatherData;
import com.comunicator.frontend.service.BackendService;
import com.comunicator.frontend.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

@Route(value = "NewsView", layout = MainLayout.class)
public class WeatherView extends HorizontalLayout {

    private TextField dateField = new TextField("Date");
    private TextField sunriseField = new TextField("Sunrise");
    private TextField sunsetField = new TextField("Sunset");
    private TextField temp = new TextField("Temperature");
    private TextField clouds = new TextField("Clouds");
    private TextField localization = new TextField("City");
    private TextField weatherDescription = new TextField("Weather is:");
    private Button getWeather = new Button("Get Weather");
    private Image weatherImg = new Image();
    private Date date;
    private Date sunrise;
    private Date sunset;
    private String exclude = "minutely,hourly,daily";
    private String apiKey = "072602db9cd1c10820dc55ee53a96f3e";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BackendService backendService;


    public WeatherView() {
        getWeather.addClickListener(click -> {
            try {
                getWeatherData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        localization.setReadOnly(true);
        dateField.setReadOnly(true);
        sunsetField.setReadOnly(true);
        sunriseField.setReadOnly(true);
        temp.setReadOnly(true);
        clouds.setReadOnly(true);
        weatherDescription.setReadOnly(true);

        add(new VerticalLayout(localization,dateField,sunriseField,sunsetField,temp, clouds, getWeather), new VerticalLayout(weatherDescription, weatherImg));
    }

    private void getWeatherData() {

        String systemipaddress = "";
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemipaddress = sc.readLine().trim();
        }
        catch (Exception e)
        {
            systemipaddress = "Cannot Execute Properly";
        }
        LocData locData = backendService.getLocData(systemipaddress);
        localization.setValue(locData.getCity());

        WeatherData weatherData = backendService.getWeatherData(locData.getLat(), locData.getLon());
        date = new Date((long)weatherData.getCurrent().getDt()*1000);
        dateField.setValue(date.toString().substring(0,16)+date.toString().substring(24, 29));
        sunrise = new Date((long)weatherData.getCurrent().getSunrise()*1000);
        sunriseField.setValue(sunrise.toString().substring(11,19));
        sunset = new Date((long)weatherData.getCurrent().getSunset()*1000);
        sunsetField.setValue(sunset.toString().substring(11,19));
        temp.setValue(weatherData.getCurrent().getTemp().toString() + " *C");
        clouds.setValue(weatherData.getCurrent().getClouds().toString() + " %");
        weatherDescription.setValue(weatherData.getCurrent().getWeather().get(0).getDescription());
        weatherImg.setSrc("http://openweathermap.org/img/wn/" + weatherData.getCurrent().getWeather().get(0).getIcon() +"@4x.png");

        InfoLogToCreate infoLogToCreate = new InfoLogToCreate();
        infoLogToCreate.setUserId(backendService.getByEmail(setEmailValue()).getId());
        infoLogToCreate.setDate(LocalDate.now().toString());
        infoLogToCreate.setType("GET_WEATHER");

        backendService.createInfoLog(infoLogToCreate);
    }

    private String setEmailValue() {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        return authentication;
    }
}
