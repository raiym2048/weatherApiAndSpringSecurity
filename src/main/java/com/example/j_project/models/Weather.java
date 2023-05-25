package com.example.j_project.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Weather {

    public Weather(String city) {
        City = city;
    }

    public Weather() {
    }
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        date = date;
    }

    public Weather(String city, String date , String temperature_min, String temperature_max, String wind_speed, String main) {
        City = city;
        this.temperature_min = temperature_min;
        this.temperature_max = temperature_max;
        this.wind_speed = wind_speed;
        this.main = main;
        this.date = date;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getTemperature_min() {
        return temperature_min;
    }

    public void setTemperature_min(String temperature_min) {
        this.temperature_min = temperature_min;
    }

    public String getTemperature_max() {
        return temperature_max;
    }

    public void setTemperature_max(String temperature_max) {
        this.temperature_max = temperature_max;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    @Id
    private String City;

    private String temperature_min;
    private String temperature_max;
    private String wind_speed;
    private String main;
}
