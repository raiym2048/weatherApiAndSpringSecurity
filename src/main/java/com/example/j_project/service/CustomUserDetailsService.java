package com.example.j_project.service;

import com.example.j_project.config.CustomUserDetails;
import com.example.j_project.models.Role;
import com.example.j_project.models.User;
import com.example.j_project.models.Weather;
import com.example.j_project.repo.RoleRepository;
import com.example.j_project.repo.UserRepository;
import com.example.j_project.repo.WeatherRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    private final WeatherRepository weatherRepository;

    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, WeatherRepository weatherRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.weatherRepository = weatherRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(
                userRepository.findByUsername(username).orElse(new User()));
    }

    @Override
    public User createUser(User newUser) {
        return createUserWithRole(newUser, "USER");
    }

    private User createUserWithRole(User newUser, String roleName) {
        //TODO: check for already existence username, if the user with username already exists throw Exception
        newUser.setId(null);
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        newUser.getRoles().add(getOrCreateRole(roleName));
        return userRepository.save(newUser);
    }

    @Override
    public User createAdmin(User newAdmin) {
        return createUserWithRole(newAdmin, "ADMIN");
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Weather> getWeathers(String city, int days) {

        /*https://api.openweathermap.org/data/2.5/forecast?q=London&appid=95877736926d93ec29e966a4b4b7c637*/
        String units = "metric";
        String apiKey = "95877736926d93ec29e966a4b4b7c637";

        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid="+apiKey;
        StringBuffer response = new StringBuffer();

        try {
            URL urls = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urls.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;


            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();

        }
        catch (Exception e){
            System.out.println(e+" error in weather catching");
            return null;
        }


        return words(response, city, days);
    }
    private List<Weather> words(StringBuffer responseS, String city, int days){
        List<Weather> weathers = new ArrayList<>();
        String response = responseS.toString();
        response = response.substring(90);
        for(int i = 0; i < days;i++){

            String temp_min = (response.substring(
                    response.indexOf("\"temp_min\"")+11, response.indexOf("\"temp_max\":")-1));
            String temp_max = (response.substring(
                    response.indexOf("\"temp_max\":")+11, response.indexOf("\"pressure\"")-1));
            String wind_speed = (response.substring(
                    response.indexOf("\"speed\"")+8, response.indexOf("\"deg\"")-1));
            String main = (response.substring(
                    response.indexOf("\"main\"")+8, response.indexOf("\"description\"")-2));
            String date = (response.substring(
                    response.indexOf("\"dt_txt\":")+10, response.indexOf("\"dt\"")-7
            ));

            response = (response.substring(response.indexOf("weather")+7));

            Weather weather = new Weather(city, date, temp_min, temp_max, wind_speed, main);
            weatherRepository.save(weather);
            weathers.add(weather);


        }


        return weathers;

    }


    public List<Weather> getByKeyword(String keyword){
        return weatherRepository.findByKeyword(keyword);
    }


    protected Role getOrCreateRole(String roleName) {
        Optional<Role> optionalRole = roleRepository.findById(roleName);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        Role role = new Role(roleName);
        return roleRepository.save(role);
    }
}
/*https://weatherapiandspringsecurity-production.up.railway.app/*/