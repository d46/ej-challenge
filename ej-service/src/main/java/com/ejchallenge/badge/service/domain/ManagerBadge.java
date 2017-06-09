package com.ejchallenge.badge.service.domain;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class ManagerBadge {

	private @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	private @ManyToOne
	Badge badge;

	private @ManyToOne
	Manager manager;

	protected ManagerBadge() {
	}

	public ManagerBadge(Manager manager, Badge badge) {
		this.manager = manager;
		this.badge = badge;
	}

}
