package com.RasikGroup.assignment_3.dtos;

import com.RasikGroup.assignment_3.entity.entities.RoleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class RoleDTO {
    private Long id;
    private String name;

    public static RoleDTO toRoleDTO(RoleEntity roleEntity){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleEntity.getId());
        roleDTO.setName(roleEntity.getName().split("_")[1].toLowerCase());
        return roleDTO;
    }
}
