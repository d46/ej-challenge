package com.ejchallange.badge.service.domain;

import lombok.Data;

import javax.persistence.Column;
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

	@Column(unique = true)
	private String action;

	private Integer scoreForBadge;

	protected Badge() {
	}

	public Badge(String name, Integer score, String action, Integer scoreForBadge) {
		this.name = name;
		this.score = score;
		this.action = action;
		this.scoreForBadge = scoreForBadge;
	}
}
