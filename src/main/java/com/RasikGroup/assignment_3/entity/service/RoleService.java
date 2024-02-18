package com.RasikGroup.assignment_3.entity.service;

import com.RasikGroup.assignment_3.entity.entities.RoleEntity;
import com.RasikGroup.assignment_3.entity.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;


    public List<RoleEntity> getAllRoles(){
        return roleRepo.findAll();
    }
}
