package com.taskflow.repository;

import com.taskflow.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();
    private Long currentId = 1L;

    public User save(User user){
        user.setId(currentId++);
        users.add(user);
        return  user;
    }

    public boolean existsByEmail(String email){
        return  users.stream().anyMatch(u -> u.getEmail().equals(email));
    }

    public Optional<User> findByEmail(String email){
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

}
