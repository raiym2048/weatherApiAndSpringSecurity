package com.example.snoopsht.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class MainController {

    @GetMapping("/")
    public String homePage() throws Exception{


        return "page";
    }
}










       /* HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://laptopdb1.p.rapidapi.com/companies"))
        .header("X-RapidAPI-Key", "ead6712697msh0a4f0c2818f256bp1dfdd8jsn44b2d7174878")
        .header("X-RapidAPI-Host", "laptopdb1.p.rapidapi.com")
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());*/