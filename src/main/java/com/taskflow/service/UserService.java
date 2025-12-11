package com.taskflow.service;


import com.taskflow.dto.request.RegisterRequest;
import com.taskflow.dto.response.UserResponse;
import com.taskflow.exception.EmailAlreadyExistsException;
import com.taskflow.exception.UserNotFoundException;
import com.taskflow.model.User;
import com.taskflow.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Register new user.
     *
     * Business rules:
     * - Email must be unique
     * - Password hashed
     *
     * @param request Registration data from client
     * @return UserResponse (without password)
     * @throws EmailAlreadyExistsException if email already exists
     */
    public UserResponse register(RegisterRequest request){

        log.info("Attempting to register user: {}", request.email());

        if(userRepository.existsByEmail(request.email())){
            log.warn("Registration failed: Email already exists - {}", request.email());
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = new User(request.email(), request.password(), request.name());
        User savedUser = userRepository.save(user);

        log.info("User registered successfully: {} with ID: {}", savedUser.getEmail(), savedUser.getId());

        return UserResponse.fromUser(savedUser);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
       }

}
