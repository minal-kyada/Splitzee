package com.tripsplit.controller;

import com.tripsplit.entity.Expense;
import com.tripsplit.entity.Group;
import com.tripsplit.entity.User;
import com.tripsplit.event.RegistrationCompleteEvent;
import com.tripsplit.exception.UserException;
import com.tripsplit.model.UserLogin;
import com.tripsplit.model.UserModel;
import com.tripsplit.service.UserService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.annotation.Timed;


import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private MeterRegistry meterRegistry;

    @PostMapping("/register")
    @Timed(value = "userRegister.time", description = "Time taken to register")
    public User registerUser(@RequestBody UserModel userModel){

        User user = null;
        try {
            user = userService.createUser(userModel);
        } catch (UserException e) {
            meterRegistry.counter("RegisterUserErrorCounter", "error_message",e.getMessage()).increment();
            throw new RuntimeException(e);
        }
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                "url"
        ));
        return user;
    }
    
    @PostMapping("/login")
    @Timed(value = "userLogin.time", description = "Time taken to login")
    public User userLogin(@RequestBody UserLogin userLogin){
        try {
            return userService.userLogin(userLogin);
        } catch (UserException e) {
            meterRegistry.counter("LoginUserErrorCount", e.getMessage());
        }
        return null;
    }
    @GetMapping("/{id}")
    @Timed(value = "fetchUserById.time", description = "Time taken to get user by id")
    public User getUserById(@PathVariable("id") Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/all")
    @Timed(value = "fetchAllUsers.time", description = "Time taken to fetch all users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/groups/{id}")
    @Timed(value = "fetchGroupsById.time", description = "Time taken to get groups by id")
    public List<Group> getUserGroups(@PathVariable("id") Long userId){
        return userService.getUserGroups(userId);
    }

    @GetMapping("/expenses/{id}")
    @Timed(value = "fetchExpenses.time", description = "Time taken to fetch expenses")
    public List<Expense> getUserExpenses(@PathVariable("id") Long userId){
        return userService.getUserExpenses(userId);
    }
    
    

    @DeleteMapping("/{id}")
    @Timed(value = "deleteUser.time", description = "Time taken to delete user")
    public String deleteUser(@PathVariable("id") Long userId){
        return userService.deleteUser(userId);
    }
}
