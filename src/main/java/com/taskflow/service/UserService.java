package com.taskflow.service;


import com.taskflow.dto.request.RegisterRequest;
import com.taskflow.dto.response.UserResponse;
import com.taskflow.exception.EmailAlreadyExistsException;
import com.taskflow.model.User;
import com.taskflow.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = new User(request.email(), request.password(), request.name());
        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId(), savedUser.getEmail(),
                savedUser.getName(), savedUser.getCreatedAt());
    }
}
