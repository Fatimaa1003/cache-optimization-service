package com.spring.redis.api.entities;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue
	private UUID id;
	private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;
    private Long contactNumber;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private char isActive='Y';
    @Column(nullable = false)
    private LocalDateTime updatedAt;
	
	 @PrePersist
	    protected void onCreate() {
	        LocalDateTime now = LocalDateTime.now();
	        this.createdAt = now;
	        this.updatedAt = now;
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }
    
}
