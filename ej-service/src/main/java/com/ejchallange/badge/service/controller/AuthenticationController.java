package com.ejchallange.badge.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class AuthenticationController {

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Index() {
		return "OK";
	}
}
