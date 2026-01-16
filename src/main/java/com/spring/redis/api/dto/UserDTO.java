package com.spring.redis.api.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class UserDTO {
	private String first_name;
	private String last_name;
	private Long contactNumber;
	private String email;
   private UUID id;
}
