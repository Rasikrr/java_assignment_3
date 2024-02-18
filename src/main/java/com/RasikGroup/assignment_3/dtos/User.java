package com.RasikGroup.assignment_3.dtos;

import com.RasikGroup.assignment_3.entity.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String first_name;
    private String last_name;
    private String username;

    public static User toModel(UserEntity userEntity){
        User user = new User(userEntity.getId(),
                userEntity.getFirst_name(),
                userEntity.getLast_name(),
                userEntity.getUsername());
        return user;
    }

    @Override
    public String toString(){
        return String.format("ID: %d, First name: %s, Last name: %s", this.id, this.first_name, this.last_name);
    }


}
