package com.example.project4.mapper;

import com.example.project4.dto.request.role.AddRoleRequest;
import com.example.project4.dto.request.user.AddUserRequest;
import com.example.project4.entity.Role;
import com.example.project4.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRoleMapper {


    public Role fromAddRoleRequest(AddRoleRequest addRoleRequest){

        Role role = new Role();
        role.setName(addRoleRequest.getName());
        role.setUsers(new ArrayList<>());  //se creaza o lista mutabila (pot adauga/sterge oricate elemente in ea

        //    role.setUsers(List.of());  //se creeaza o lista imutabila (nu mai ot adauga/sterge elemente din ea, aici e gresita ca nu mai pot adauga utilizatori in ea

        return role;
    }


    public User fromAddUserRequest(AddUserRequest addUserRequest){

        User user = new User();
        user.setUsername(addUserRequest.getUsername());
        user.setPassword(addUserRequest.getPassword());
        user.setEmail(addUserRequest.getEmail());

        return user;
    }

}
