package com.PusleckiKusiak.Life.controler;
import com.PusleckiKusiak.Life.entity.DailyQuest;
import com.PusleckiKusiak.Life.entity.User;
import com.PusleckiKusiak.Life.exception.ApiRegisterException;
import com.PusleckiKusiak.Life.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        if(!userRepository.existsUserByUsername(user.getUsername())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        else{
            throw  new ApiRegisterException("This username is already in use");
        }
    }
    @GetMapping()
    public List<User> getUser(){
        return userRepository.findAll();
    }
    @GetMapping("/{userName}")
    public User getUserInfo(@PathVariable String userName){
        User tmp = userRepository.findByUsername(userName);
        tmp.setPassword("");
        return tmp;
    }
}