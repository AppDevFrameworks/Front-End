package com.phillies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MainController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String error(Model model) {
		return "error";
	}
}