package com.tripsplit.controller;

import com.tripsplit.entity.Expense;
import com.tripsplit.entity.Group;
import com.tripsplit.entity.User;
import com.tripsplit.event.RegistrationCompleteEvent;
import com.tripsplit.model.UserLogin;
import com.tripsplit.model.UserModel;
import com.tripsplit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserModel userModel){

        User user =userService.createUser(userModel);

        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                "url"
        ));
        return user;
    }
    
    @PostMapping("/login")
    public User userLogin(@RequestBody UserLogin userLogin) throws Exception{

        return userService.userLogin(userLogin);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/groups/{id}")
    public List<Group> getUserGroups(@PathVariable("id") Long userId){
        return userService.getUserGroups(userId);
    }

    @GetMapping("/expenses/{id}")
    public List<Expense> getUserExpenses(@PathVariable("id") Long userId){
        return userService.getUserExpenses(userId);
    }
    
    

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long userId){
        return userService.deleteUser(userId);
    }
}
