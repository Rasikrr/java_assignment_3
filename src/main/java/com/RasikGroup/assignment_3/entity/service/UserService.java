package com.RasikGroup.assignment_3.entity.service;

import com.RasikGroup.assignment_3.dtos.RegistrationRequest;
import com.RasikGroup.assignment_3.entity.entities.RoleEntity;
import com.RasikGroup.assignment_3.entity.entities.UserEntity;
import com.RasikGroup.assignment_3.entity.repository.RoleRepo;
import com.RasikGroup.assignment_3.entity.repository.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username);
        if(userEntity == null){
            throw new UsernameNotFoundException("User not found");
        }
        return userEntity;
    }

    public UserEntity findUserById(Long id){
        Optional<UserEntity> userEntity = userRepo.findById(id);
        return userEntity.orElse(new UserEntity());
    }

    public List<UserEntity> allUsers(){
        return userRepo.findAll();
    }

    public boolean saveUser(RegistrationRequest registrationRequest) {
        UserEntity userFromDB = userRepo.findByUsername(registrationRequest.getUsername());

        if (userFromDB != null) {
            return false;
        }
        System.out.println(registrationRequest.getPassword());
        System.out.println(registrationRequest.getPasswordConfirm());
        System.out.println(registrationRequest.getUsername());
        System.out.println(registrationRequest.getFirst_name());
        System.out.println(registrationRequest.getLast_name());


        UserEntity user = new UserEntity();

        RoleEntity role = roleRepo.getById(2L);
        user.setRoles(Set.of(role));
        user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        userRepo.save(user);
        return true;
    }

    public boolean deleteUser(Long id) {
        if (userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
