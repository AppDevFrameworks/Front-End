package com.phillies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.phillies.domain.Order;
import com.phillies.repository.FlowerRepo;

@Controller
public class Controllers {

	@Autowired
	FlowerRepo flowerRepo;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Order order) {
		return "index";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String orderSubmit(Order order, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		model.addAttribute("firstName", order.getFirstName());
		model.addAttribute("lastName", order.getLastName());
		return "orderReturn";
	}
	
//	@GetMapping("/displayOne/{id}")
//	public String showMyDetails(@PathVariable int id, Model model)
//	{
//		Flower f = (Flower) flowerRepo.findOne((int) id);
//		model.addAttribute("flower", f);
//		return "displayOne";
//	}
//	
//
//	@GetMapping("/displayall")
//	public String displayAll(Model model)
//	{
//		List<Flower> f = flowerRepo.findAll();
//		model.addAttribute("flowers", f);
//		return "displayAll";
//	}
	

}
