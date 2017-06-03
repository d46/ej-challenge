package com.ejchallange.badge.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@ToString(exclude = "password")
@Entity
public class User
{
	public static PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	private @Id @GeneratedValue Long id;
	private String username;
	//Ignores to send a hashed password
	private @JsonIgnore String password;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	//Construct for user model
	public User(String username,String password){
		this.username = username;
		this.setPassword(password);
	}

}
