package com.spring.redis.api.service;

import java.util.List;
import java.util.UUID;

import com.spring.redis.api.dto.UserDTO;


public interface IUserService {
UserDTO createUser(UserDTO userDTO);
UserDTO getUserById(UUID id);
void deleteUserById(UUID id);
List<UserDTO>getAllUser();
UserDTO updateUserById(UUID id, UserDTO userDTO);
}
