package com.phillies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phillies.domain.Account;
import com.phillies.domain.Flower;
import com.phillies.domain.FlowerPackage;
import com.phillies.domain.Order;
import com.phillies.repository.FlowerRepo;
import com.phillies.repository.PackageRepo;

@Controller
public class Controllers {

	@Autowired
	FlowerRepo flowerRepo;
	
	@Autowired
	PackageRepo packageRepo;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model, Order order) {
		model.addAttribute("order", order);
		List<FlowerPackage> packages =  packageRepo.findAll();
		model.addAttribute("packages", packages);
		System.out.println(packages.get(0).getName());
		return "index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		return "login";

	}
	
	@RequestMapping(value="/order", method=RequestMethod.POST)
	public String orderSubmit(Order order, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		model.addAttribute("firstName", order.getFirstName());
		model.addAttribute("lastName", order.getLastName());
		return "orderReturn";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginUser(Account account, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		model.addAttribute("firstName", account.getFirstname());
		model.addAttribute("lastName", account.getLastname());
		return "loginDashboard";
	}

}
