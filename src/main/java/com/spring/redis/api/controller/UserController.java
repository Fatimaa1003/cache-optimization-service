package com.spring.redis.api.controller;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.redis.api.dto.UserDTO;
import com.spring.redis.api.service.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
	private final IUserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDTO>createUser(@RequestBody UserDTO user){
		log.info("createUser method from UserController got called");
		return ResponseEntity.ok(userService.createUser(user));
	}
@GetMapping("/getUsers")
public ResponseEntity<List<UserDTO>>getAllUser(){
	log.info("getAllUser method from UserController got called");
	return ResponseEntity.ok(userService.getAllUser());
}
@DeleteMapping("deleteUser/{id}")
public void deleteUser(@PathVariable UUID id){
	log.info("deleteUser method from UserController got called");
	userService.deleteUserById(id);
}
@GetMapping("/getUserById/{id}")
public ResponseEntity<UserDTO>getUserById(@PathVariable UUID id){
	log.info("getUserById method from UserController got called");
	return ResponseEntity.ok(userService.getUserById(id));
}
@PutMapping("updateUser/{id}")
public ResponseEntity<UserDTO>updateUserById(@PathVariable UUID id,@RequestBody UserDTO user){
	log.info("updateUserById method got called from UserController");
	return ResponseEntity.ok(userService.updateUserById(id,user));
}
}