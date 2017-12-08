package com.phillies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phillies.domain.Account;
import com.phillies.domain.Order;
import com.phillies.services.AccountService;
import com.phillies.services.OrderService;

@Controller
public class LoginController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private OrderService orderService;

	@GetMapping("/login")
	public String login(Model model) {
		if (!model.containsAttribute("user"))
			return "login";
		return "redirect:/index";
	}

	@PostMapping("/login")
	public String processLogin(Model model, @RequestParam String name, @RequestParam String pass) {
		Account account = (Account) getAccount(name, pass);
		if (account==null)
			return "login";
		else {
			model.addAttribute("user", account);
			return "index";
		}
	}

	public Account getAccount(String name, String pass) {
		Account temp = accountService.login(name, pass);
		if(temp!=null) {
			
		}
		return temp;
	}
	
	public List<Order> getOrders(String name) {
		return orderService.getUserOrders(name);
	}
}