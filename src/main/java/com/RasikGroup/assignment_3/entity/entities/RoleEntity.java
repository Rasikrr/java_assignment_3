package com.RasikGroup.assignment_3.entity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_role")
public class RoleEntity implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> userEntities;

    public RoleEntity(Long id, String name){
        this.setId(id);
        this.setName(name);
    }


    @Override
    public String getAuthority() {
        return getName();
    }


}
