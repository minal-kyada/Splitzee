package com.tripsplit.service;

import com.tripsplit.entity.Expense;
import com.tripsplit.entity.Group;
import com.tripsplit.entity.User;
import com.tripsplit.model.UserLogin;
import com.tripsplit.model.UserModel;

import java.util.List;


public interface UserService {
    User createUser(UserModel userModel);

    User getUserById(Long userId);

    List<Group> getUserGroups(Long userId);

    List<Expense> getUserExpenses(Long userId);

    List<User> getAllUsers();

    String deleteUser(Long userId);

    User userLogin(UserLogin userLogin);

}
