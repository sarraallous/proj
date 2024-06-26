package com.example.hotels.Repository;

import com.example.hotels.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    User findByNomAndMdp(String nom, String mdp);
    User findByNom(String nom);
    User findByNumTel(String numTel);
    User findByEmail(String email);
}