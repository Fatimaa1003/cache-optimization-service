package com.spring.redis.api.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.spring.redis.api.dto.UserDTO;
import com.spring.redis.api.entities.User;
import com.spring.redis.api.mapper.UserMapper;
import com.spring.redis.api.repository.UserRepository;
import com.spring.redis.api.service.IUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService implements IUserService{

    
	private final UserMapper userMapper;
	private final UserRepository userRepository;
	@CachePut(value = "Users", key = "#result.id")

    @Override
	public UserDTO createUser(UserDTO userDTO) {
		log.info("createUser method from userService got called");
		User user=userMapper.dtoToEnttity(userDTO);
		User savedUser=userRepository.save(user);
		return userMapper.entityToDto(savedUser);
	}
	@Cacheable(value = "Users", key = "#id")
	@Override
	public UserDTO getUserById(UUID id) {
		log.info("Fetching user from DATABASE");
		log.info("getUserById method got called from UserService");
		User user=userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Invalid id"));
		if (user.getIsActive() == 'N') {

			throw new RuntimeException("NOT FOUND");
		}

		return userMapper.entityToDto(user);
	
	}
@CacheEvict(value="Users", key= "#id")
	@Override
	public void deleteUserById(UUID id) {
		log.info("userDelete method from UserService got deleted");
		User user=userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Invalid id"));
		user.setIsActive('N');
		userRepository.save(user);
		log.info("user got deleted");
	}

	@Override
	public List<UserDTO> getAllUser() {
		log.info("getAllUser method got called");
		List<UserDTO>list=userRepository.getAllService().stream().map(e -> userMapper.entityToDto(e))
	.toList();
return list;
	}
	@CachePut(value = "users", key = "#id")
	@Override
	public UserDTO updateUserById(UUID id, UserDTO userDTO) {

	    log.info("updateUserById method got called from UserService");

	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Invalid id"));

	    if (user.getIsActive() == 'N') {
	        throw new RuntimeException("Invalid User");
	    }

	   
	    user.setFirst_name(userDTO.getFirst_name());
	    user.setLast_name(userDTO.getLast_name());
	    user.setContactNumber(userDTO.getContactNumber());
	    user.setEmail(userDTO.getEmail());

	    User updatedUser = userRepository.save(user);

	    return userMapper.entityToDto(updatedUser);
	}

}