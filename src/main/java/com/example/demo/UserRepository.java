package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    // returns User by its username: assuming these will be unique!
    // called by the SSUserDetailsService class

    Long countByUsername(String username);
    // returns number of usernames present in database

    User findByEmail(String email);
    // returns User matching the email field: assuming these will always be unique!

    Long countByEmail(String email);
    // returns number of emails present in the database
}