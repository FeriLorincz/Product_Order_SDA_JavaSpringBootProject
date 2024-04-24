package com.example.project4.service;

import com.example.project4.dto.request.role.AddRoleRequest;
import com.example.project4.dto.request.user.AddUserRequest;
import com.example.project4.entity.Role;
import com.example.project4.entity.User;
import com.example.project4.mapper.UserRoleMapper;
import com.example.project4.repository.RoleRepository;
import com.example.project4.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRoleMapper userRoleMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleMapper = userRoleMapper;
    }


    @Override
    public void addRole(AddRoleRequest addRoleRequest) {

        Role role = userRoleMapper.fromAddRoleRequest(addRoleRequest);
        roleRepository.save(role);
    }

    @Override
    public void addUser(AddUserRequest addUserRequest) {

        User user = userRoleMapper.fromAddUserRequest(addUserRequest);

        List<String> userRoles = addUserRequest.getRoleName(); //e o lista de stringuri parsata in body

        List<Role> roles = roleRepository.findAll().stream().filter(element ->userRoles.contains(element.getName())).collect(Collectors.toList());

        user.setRoles(roles);
        userRepository.save(user);

    }
}
