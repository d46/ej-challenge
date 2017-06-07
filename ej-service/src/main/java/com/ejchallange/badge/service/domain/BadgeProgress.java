package com.ejchallange.badge.service.domain;

import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class BadgeProgress {

	private @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;


	private @ManyToOne
	Badge badge;

	private @ManyToOne
	Manager manager;

	protected BadgeProgress() {
	}

	public BadgeProgress(Badge badge, Manager manager) {
		this.badge = badge;
		this.manager = manager;
	}

}
