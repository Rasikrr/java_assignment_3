package com.RasikGroup.assignment_3.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String first_name;
    private String last_name;
    private String username;
    private String password;
//    private Long role;
    private String passwordConfirm;
}
