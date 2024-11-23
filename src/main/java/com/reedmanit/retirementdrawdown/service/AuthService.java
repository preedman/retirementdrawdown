package com.reedmanit.retirementdrawdown.service;

import com.reedmanit.retirementdrawdown.model.User;
import com.reedmanit.retirementdrawdown.model.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  //  private UserRepository userRepository;

    public AuthService() {

    }

    public boolean Authenticate(String username, String password) {
       User user = UserRepository.findUser(username);
       boolean authenticated = false;
       if (user != null) {
           if (user.checkPassword(password)) {
               authenticated = true;
           } else {
               authenticated = false;
           }
       }
       return authenticated;
    }
}
