package com.maltem.movie.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieWebController {


	@RequestMapping("/")
	public String movie(Map<String, Object> model) {
		//model.put("message", this.message);
		return "movies";
	}

}