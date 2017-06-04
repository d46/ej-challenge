package com.ejchallange.badge.service.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Badge {

	private @Id
	@GeneratedValue
	Long id;

	private String name;

	private Integer score;

	protected Badge() {
	}

	public Badge(String name, Integer score) {
		this.name = name;
		this.score = score;
	}

}
