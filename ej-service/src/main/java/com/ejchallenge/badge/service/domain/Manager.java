package com.ejchallenge.badge.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Data
@ToString(exclude = "password")
@Entity
public class Manager {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	private @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;

	@Column(unique = true)
	private String username;

	//Ignores to send a hashed password
	private @JsonIgnore
	String password;

	private String[] roles;

	private int totalScore;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	protected Manager() {

	}

	//Construct for user model
	public Manager(String username, String password, int totalScore, String... roles) {
		this.username = username;
		this.setPassword(password);
		this.totalScore = totalScore;
		this.roles = roles;
	}

}
