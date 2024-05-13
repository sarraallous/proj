package com.example.hotels.Repository;

import com.example.hotels.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    public User findById(int id);
    public User findBynom(String nom);
    public User findBynumTel(String numTel);
    public User findByemail(String email);
}
