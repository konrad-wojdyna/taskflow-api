package com.taskflow.repository;

import com.taskflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByEmail(String email);

   boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmailWithQuery(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.createdAt > :date")
    List<User> findRecentUsers(@Param("date") LocalDateTime date);

    @Query(value = "SELECT * FROM users WHERE email LIKE %:keyword%", nativeQuery = true)
    List<User> searchByEmailNative(@Param("keyword") String keyword);


   @Query("SELECT u FROM User u WHERE u.email = :email AND u.name = :name")
   Optional<User> findByEmailAndName(@Param("email") String email,
                                     @Param("name") String name);
}
