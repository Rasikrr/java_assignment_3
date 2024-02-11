package com.RasikGroup.assignment_3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name is required")
    @NotBlank(message = "First name is required")
    private String first_name;

    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    private String last_name;

    @NotNull(message = "Username is required")
    @NotBlank(message = "username is required")
    private String username;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 5, max=25, message = "Password must be between 5 and 25 characters")
    private String password;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;

    @Override
    public String toString(){
        return String.format("%s %s %s\n", getUsername(), getEmail(), getPassword());
    }
}