package com.ejchallenge.badge.service.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Action {

	private @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id;

	@Column(unique = true)
	private String name;

	private @ManyToOne
	Badge badge;

	private int score;

	protected Action(){

	}

	public Action(String name,int score, Badge badge){
		this.name = name;
		this.score = score;
		this.badge = badge;
	}

}
