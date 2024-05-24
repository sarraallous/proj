package com.example.hotels.Services;

import com.example.hotels.Entities.User;
import com.example.hotels.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Assurez-vous d'avoir une interface UserRepository pour interagir avec la base de données

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User updateUser(int id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setNom(user.getNom());
            updatedUser.setPrenom(user.getPrenom());
            updatedUser.setMdp(user.getMdp());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setNumTel(user.getNumTel());

            return userRepository.save(updatedUser);
        } else {
            return null;
        }
    }

    public User login(String nom, String mdp) {
        return userRepository.findByNomAndMdp(nom, mdp);
    }

    public User registerUser(User user) {
        user.setEtat(user.getEmail() != null && user.getEmail().toLowerCase().endsWith("@admin.com"));
        return addUser(user);
    }

    public User authenticate(String nom, String mdp) {
        User user = userRepository.findByNomAndMdp(nom, mdp);
        return user;
    }

    public boolean isAdmin(int userId) {
        User user = findById(userId);
        return user != null && user.isEtat();
    }
}
