package com.phillies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.phillies.domain.Flower;
import com.phillies.repository.FlowerRepo;

@Controller
public class Controllers {

	@Autowired
	FlowerRepo flowerRepo;

	@GetMapping("/")
	public String doWelcomeWithParams(Model model)
	{
		String a = "Hello World";
		model.addAttribute("hello", a);
		return "index";
	}

	//	@GetMapping("/usingParameter")
	//	public String doWelcomeWithParams(@RequestParam(value="name", defaultValue="To You!")String name, Model model)
	//	{
	//	String sentence = "Welcome " + name;
	//	model.addAttribute("message", sentence);
	//	return "parameter";
	//	}
	//	
	@GetMapping("/displayall")
	public String displayAll(Model model)
	{
		List<Flower> f = flowerRepo.findAll();
		model.addAttribute("flowers", f);
		return "displayAll";
	}

	@GetMapping("/displayOne/{id}")
	public String showMyDetails(@PathVariable int id, Model model)
	{
		Flower f = (Flower) flowerRepo.findOne((int) id);
		model.addAttribute("flower", f);
		return "displayOne";
	}


}
