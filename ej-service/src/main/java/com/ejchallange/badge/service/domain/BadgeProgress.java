package com.ejchallange.badge.service.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class BadgeProgress {

	private @Id
	@GeneratedValue
	Long id;


	private @ManyToOne
	Badge badge;

	private @ManyToOne
	User user;

	protected BadgeProgress() {
	}

	public BadgeProgress(Badge badge, User user) {
		this.badge = badge;
		this.user = user;
	}

}
