package com.RasikGroup.assignment_3.conroller;

import com.RasikGroup.assignment_3.entity.entities.RoleEntity;
import com.RasikGroup.assignment_3.entity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RolesController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public @ResponseBody List<RoleEntity> getAll(){
        return roleService.getAllRoles();

    }

}
