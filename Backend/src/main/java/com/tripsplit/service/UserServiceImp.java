package com.tripsplit.service;

import com.tripsplit.entity.Expense;
import com.tripsplit.entity.Group;
import com.tripsplit.entity.User;
import com.tripsplit.exception.UserException;
import com.tripsplit.model.UserLogin;
import com.tripsplit.model.UserModel;
import com.tripsplit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserModel userModel) throws UserException {
        User user = new User();
        String stringRegex = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
        String fn = userModel.getUserFirstName();
        Matcher fnMatch = Pattern.compile(stringRegex).matcher(fn);
        if (fn.isEmpty() || fn.isBlank() || !fnMatch.matches()) {
            throw new UserException("Invalid First Name");
        }
        user.setUserFirstName(fn);

        String ln = userModel.getUserLastName();
        Matcher lnMatch = Pattern.compile(stringRegex).matcher(ln);
        if (ln.isEmpty() || ln.isBlank() || !lnMatch.matches()) {
            throw new UserException("Invalid Last Name");
        }
        user.setUserLastName(ln);

        String userNameRegex = "^[A-Za-z]\\\\w{5, 29}$";
        String un = userModel.getUserName();
        Matcher unMatch = Pattern.compile(userNameRegex).matcher(un);
        if (un.isEmpty() || un.isBlank() || !unMatch.matches()) {
            throw new UserException("Invalid User Name");
        }
        user.setUserName(un);

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
    public User userLogin(UserLogin userLogin) throws UserException {
        User user = userRepository.findByUserName(userLogin.getUsername());
        if(user == null){
            throw new UserException("User not found");
        }
        if(passwordEncoder.matches(userLogin.getPassword(),user.getUserPassword())){
            return user;
        }
        return null;
    }

}
