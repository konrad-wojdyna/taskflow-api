package com.taskflow.service;


import com.taskflow.dto.request.LoginRequest;
import com.taskflow.dto.request.RegisterRequest;
import com.taskflow.dto.response.AuthResponse;
import com.taskflow.dto.response.UserResponse;
import com.taskflow.exception.EmailAlreadyExistsException;
import com.taskflow.exception.InvalidCredentialsException;
import com.taskflow.exception.UserNotFoundException;
import com.taskflow.model.User;
import com.taskflow.repository.UserRepository;
import com.taskflow.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
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

        String hashedPassword = passwordEncoder.encode(request.password());

        User user = new User(request.email(), hashedPassword, request.name());
        User savedUser = userRepository.save(user);

        log.info("User registered successfully: {} with ID: {}", savedUser.getEmail(), savedUser.getId());

        return UserResponse.fromUser(savedUser);
    }

    public AuthResponse login(LoginRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email()).orElseThrow(InvalidCredentialsException::new);

        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new InvalidCredentialsException();
        }

        //Generate JWT token
        String token = jwtUtils.generateToken(user.getEmail());

        return new AuthResponse(token, UserResponse.fromUser(user));

    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
       }

}
