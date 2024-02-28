package com.RasikGroup.assignment_3.conroller;

import com.RasikGroup.assignment_3.dtos.LoginRequest;
import com.RasikGroup.assignment_3.dtos.RegistrationRequest;
import com.RasikGroup.assignment_3.dtos.UserDTO;
import com.RasikGroup.assignment_3.entity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    UserService userService;


    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegistrationRequest registrationRequest){
        boolean ans = userService.saveUser(registrationRequest);
        return ResponseEntity.ok().body(String.format("{\"response\": \"%b\"}", ans));
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<UserDTO> authenticateUser(@RequestBody LoginRequest loginRequest) {
        UserDTO userDTO = userService.login(loginRequest);
        if(userDTO.getUsername() != null) {
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.badRequest().body(userDTO);
    }

}
