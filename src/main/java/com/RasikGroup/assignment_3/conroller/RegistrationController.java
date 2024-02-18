package com.RasikGroup.assignment_3.conroller;

import com.RasikGroup.assignment_3.dtos.RegistrationRequest;
import com.RasikGroup.assignment_3.entity.entities.UserEntity;
import com.RasikGroup.assignment_3.entity.service.UserService;
import jakarta.validation.GroupSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity signup(@RequestBody RegistrationRequest registrationRequest){
        boolean ans = userService.saveUser(registrationRequest);
        return ResponseEntity.ok().body(String.format("{\"response\": \"%b\"}", ans));

    }


}
