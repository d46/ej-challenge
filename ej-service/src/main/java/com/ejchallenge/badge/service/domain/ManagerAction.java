package com.ejchallenge.badge.service.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ManagerAction {

	private @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id;

	private @ManyToOne
	Action action;

	private @ManyToOne
	Manager manager;

	protected ManagerAction() {

	}

	public ManagerAction(Manager manager, Action action) {
		this.manager = manager;
		this.action = action;
	}

}
