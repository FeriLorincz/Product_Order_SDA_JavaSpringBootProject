package com.example.project4.service;

import com.example.project4.dto.request.role.AddRoleRequest;
import com.example.project4.dto.request.user.AddUserRequest;

public interface UserRoleService {

    public void addRole(AddRoleRequest addRoleRequest);

    public void addUser(AddUserRequest addUserRequest);


}
