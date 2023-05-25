package com.example.j_project.controller;


import com.example.j_project.bootstrap.EmailSenderService;
import com.example.j_project.models.Adress;
import com.example.j_project.models.Product;
import com.example.j_project.models.User;
import com.example.j_project.models.Weather;
import com.example.j_project.repo.ProductRepo;
import com.example.j_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private EmailSenderService senderService;


    private final UserService userService;
    List<String> listSort = new ArrayList<>();
    private int theDays = 40;

    private String sortWord = "By Id Asc";
    private String cityName = "Bishkek";

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model model){

        return "register";
    }
    @GetMapping("/")
    public String mainPage(){
        return "mainPage";
    }
    @GetMapping("/users")
    public String usersPage(Model model){

        model.addAttribute("users",userService.findAllUsers());
        log.debug("\n\n\n Registering user: " + userService.findAllUsers()+"\n\n\n");

        return "users";

    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute User user){
        log.debug("Registering user: " + user.toString());
        userService.createUser(user);
        return "login";
    }
    @GetMapping("/weather")
    public String getweather(Model model){
        model.addAttribute("weather",userService.getWeathers(cityName, 40));

        return "weather";
    }
    @GetMapping("/weather/days")
    public String weatherDays (@ModelAttribute("days") int days, Model model){
        theDays = days;
        model.addAttribute("weather", userService.getWeathers(cityName, days));
        return "weather";
    }

    @RequestMapping(path = {"/search"})
    public String home(Model model, String keyword) {

        if(keyword!=null) {
/*
            List<Weather> list = userService.getWeathers(keyword, theDays);
*/
            model.addAttribute("weather", userService.getWeathers(keyword, theDays));
            cityName = keyword;
        }else {
            List<Weather> list = userService.getWeathers("Bishkek", theDays);
            model.addAttribute("weather", list);}

        return "weather";
    }

    @RequestMapping(path = {"/product"})
    public String productSort(Model model, String keyword) {
        listSort.clear();
        listSort.add("By Id Asc");listSort.add("By Id Desc");listSort.add("By Product Name Asc");
        listSort.add("By Product Name Desc");listSort.add("By Data Asc");listSort.add("By Data Desc");
        model.addAttribute("list", listSort);

        if(keyword!=null) {
            switch (keyword){
                case "By Id Asc":
                    model.addAttribute("product", productRepo.findByOrderByIdAsc());
                    break;
                case "By Id Desc":
                    model.addAttribute("product", productRepo.findByOrderByIdDesc());
                    break;
                case "By Product Name Asc":
                    model.addAttribute("product", productRepo.findByOrderByProductNameAsc());
                    break;
                case "By Product Name Desc":
                    model.addAttribute("product", productRepo.findByOrderByProductNameDesc());
                    break;
                case "By Data Asc":
                    model.addAttribute("product", productRepo.findByOrderByDataAsc());
                    break;
                case "By Data Desc":
                    model.addAttribute("product", productRepo.findByOrderByDataDesc());
                    break;
            }
            sortWord = keyword;


        }else {

            model.addAttribute("product", productRepo.findByOrderByIdAsc());}

        return "product";
    }
    @GetMapping("/product/{id}")
    public String productBuy (@PathVariable("id") Long id, Model model){
        model.addAttribute("product", productRepo.findById(id));
        return "buy";
    }
    @PostMapping("/product/{id}")
    public String productBuyPost (@ModelAttribute Adress adress, Model model){
        Optional<Product> product = productRepo.findById(adress.getProduct_id());
        senderService.sendEmail(adress.getEmail().toString(), adress.getAdress(), adress.getPhone_number(), adress.getProduct_id());


        return "check";
    }

    @GetMapping("/about")
    public String about(){

        return "about";
    }
}




