package com.crio.xlido.services;

import java.util.Optional;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IUserRepository;

public class UserService {
    
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository =userRepository;
    }


    public User createUser(String email , String password){
        User newUser = new User(email , password);
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }
}
