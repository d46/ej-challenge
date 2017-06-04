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

	private String action;

	protected Badge() {
	}

	public Badge(String name, Integer score, String action) {
		this.name = name;
		this.score = score;
		this.action = action;
	}
}
