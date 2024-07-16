package com.xlri.prometheus;

import com.xlri.prometheus.response.BaseResponse;
import com.xlri.prometheus.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signup")
    public BaseResponse signUp(@RequestBody User user) {
        //System.out.println(user);
        return userService.saveUser(user);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public UserResponse logIn(@RequestBody User user) {
        //System.out.println(user);
        return userService.findByUsername(user);
    }

}