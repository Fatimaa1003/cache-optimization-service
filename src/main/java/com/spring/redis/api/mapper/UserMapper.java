package com.spring.redis.api.mapper;

import org.springframework.stereotype.Component;

import com.spring.redis.api.dto.UserDTO;
import com.spring.redis.api.entities.User;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class UserMapper {
	public User dtoToEnttity(UserDTO userDTO) {
		log.info("dtoToEntity method got called from UserMapper ");
		User user=new User();
		user.setFirst_name(userDTO.getFirst_name());
		user.setLast_name(userDTO.getLast_name());
		user.setContactNumber(userDTO.getContactNumber());
		user.setEmail(userDTO.getEmail());
		user.setId(userDTO.getId());
		log.info("dtoToEntity method from UserMapper successfully called");
		return user;
	}
	public UserDTO entityToDto(User user) {
		log.info("entityToDto method from UserMapper got called");
		UserDTO userDTO=new UserDTO();
		userDTO.setFirst_name(user.getFirst_name());
		userDTO.setLast_name(user.getLast_name());
		userDTO.setContactNumber(user.getContactNumber());
		userDTO.setEmail(user.getEmail());
		userDTO.setId(user.getId());
		log.info("entityToDto method from UserMapper successfully called");
		return userDTO;
	}

}
