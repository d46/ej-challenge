package com.ejchallange.badge.service.dto;

import lombok.Data;

@Data
public class BadgeDto {

	private int score;

	private String name;

	public BadgeDto(int score, String name) {
		this.score = score;
		this.name = name;
	}

}
