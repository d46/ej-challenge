package com.ejchallenge.badge.service.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Badge {

	private @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;

	private String name;


	protected Badge() {
	}

	public Badge(String name) {
		this.name = name;
	}
}
