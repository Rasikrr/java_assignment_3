package com.RasikGroup.assignment_3.entity.service;

import com.RasikGroup.assignment_3.dtos.RoleDTO;
import com.RasikGroup.assignment_3.entity.entities.RoleEntity;
import com.RasikGroup.assignment_3.entity.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;


    public List<RoleDTO> getAllRoles(){
        List<RoleDTO> roleDTOS= new ArrayList<>();
        List<RoleEntity> roleEntities = roleRepo.findAll();
        for(RoleEntity e: roleEntities){
            if(!e.getName().equals("ROLE_ADMIN")) {
                roleDTOS.add(RoleDTO.toRoleDTO(e));
            }
        }
        return roleDTOS;
    }
}
