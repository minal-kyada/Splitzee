package com.tripsplit.service;

import com.tripsplit.entity.Expense;
import com.tripsplit.entity.Group;
import com.tripsplit.entity.User;
import com.tripsplit.model.UserLogin;
import com.tripsplit.model.UserModel;
import com.tripsplit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserModel userModel) {
        User user = new User();

        user.setUserFirstName(userModel.getUserFirstName());
        user.setUserLastName(userModel.getUserLastName());
        user.setUserName(userModel.getUserName());
        user.setUserPassword(passwordEncoder.encode(userModel.getUserPassword()));
        user.setUserGroups(null);
        user.setRole("USER");
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<Group> getUserGroups(Long userId) {
        return userRepository.findById(userId).get().getUserGroups();
    }

    @Override
    public List<Expense> getUserExpenses(Long userId) {
        return userRepository.findById(userId).get().getExpenses();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "user deleted";
    }

    @Override
    public User userLogin(UserLogin userLogin) {
        User user = userRepository.findByUserName(userLogin.getUsername());
        if(user ==null){
            return null;
        }
        if(passwordEncoder.matches(userLogin.getPassword(),user.getUserPassword())){
            return user;
        }
        return null;
    }

}
