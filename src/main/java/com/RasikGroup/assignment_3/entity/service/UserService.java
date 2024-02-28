package com.RasikGroup.assignment_3.entity.service;

import com.RasikGroup.assignment_3.dtos.LoginRequest;
import com.RasikGroup.assignment_3.dtos.RegistrationRequest;
import com.RasikGroup.assignment_3.dtos.UserDTO;
import com.RasikGroup.assignment_3.entity.entities.RoleEntity;
import com.RasikGroup.assignment_3.entity.entities.UserEntity;
import com.RasikGroup.assignment_3.entity.repository.RoleRepo;
import com.RasikGroup.assignment_3.entity.repository.UserRepo;
import jakarta.persistence.EntityManager;
import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;



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

        UserEntity user = new UserEntity();
        RoleEntity role = roleRepo.getById(registrationRequest.getRole_id());
        user.setRoles(Set.of(role));
        user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        user.setFirst_name(registrationRequest.getFirst_name());
        user.setLast_name(registrationRequest.getLast_name());
        user.setUsername(registrationRequest.getUsername());
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

    public UserDTO login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDTO userDTO = new UserDTO();
        UserEntity userEntity = userRepo.findByUsername(authentication.getName());
        userDTO.setUsername(authentication.getName());
        userDTO.setRoles(authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())); // Assuming authorities are strings
        userDTO.set_auth(authentication.isAuthenticated()); // Renamed to follow standard naming conventions
        userDTO.setId(userEntity.getId());
        return userDTO;
    }


}
