package com.example.project4.controller;

import com.example.project4.dto.request.role.AddRoleRequest;
import com.example.project4.dto.request.user.AddUserRequest;
import com.example.project4.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class UserRoleController {


    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/role")
    public ResponseEntity<Void> addRole(@RequestBody AddRoleRequest addRoleRequest){
        userRoleService.addRole(addRoleRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user")
    public ResponseEntity<Void> addUser(@RequestBody AddUserRequest addUserRequest){
        userRoleService.addUser((addUserRequest));
        return ResponseEntity.ok().build();
    }




}
