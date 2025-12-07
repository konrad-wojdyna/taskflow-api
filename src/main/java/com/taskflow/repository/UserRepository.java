package com.taskflow.repository;

import com.taskflow.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private final Map<Long, User> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public User save(User user){

        if(user.getId() == null){
        user.setId(idGenerator.getAndIncrement());
        }

        storage.put(user.getId(), user);
        return  user;
    }

    public boolean existsByEmail(String email){
        return  storage.values().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public Optional<User> findByEmail(String email){
        return storage.values().stream().filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<User> findById(Long id){
        return Optional.ofNullable(storage.get(id));
    }

    public List<User> findAll(){
        return new ArrayList<>(storage.values());
    }

    public void deleteAll(){
        storage.clear();
        idGenerator.set(1);
    }

}
