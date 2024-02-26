package com.RasikGroup.assignment_3.dtos;

import com.RasikGroup.assignment_3.entity.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private Collection<String> roles;
    private boolean is_auth;

}
