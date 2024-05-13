package com.example.hotels.Services;

import com.example.hotels.Entities.Facture;
import com.example.hotels.Entities.User;
import com.example.hotels.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> findAll()
    {
        return userRepository.findAll();
    }
    public User findById(int id){
        User user = userRepository.findById(id);
        return user;
    }
    public User addUser(User c){
        User co = userRepository.save(c);
        return c;
    }
    public void DeleteFacture(int id) {

        userRepository.deleteById(id);

    }
    public User updateUser(int id , User e) {

        User eq = userRepository.findById(id);
        eq.setUserId(id);
        eq.setNom(e.getNom());
        eq.setPrenom(e.getPrenom());
        eq.setMdp(e.getMdp());
        eq.setMdp(e.getMdp());
        eq.setEmail(e.getEmail());
        eq.setNumTel(e.getNumTel());
        eq.setEtat(e.getEtat());
        userRepository.save(eq);
        return eq;

    }
}
