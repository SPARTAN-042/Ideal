package com.spartan.ideal.service;

import com.spartan.ideal.model.User;
import com.spartan.ideal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private static final List<User> users = new ArrayList<>();

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

}
