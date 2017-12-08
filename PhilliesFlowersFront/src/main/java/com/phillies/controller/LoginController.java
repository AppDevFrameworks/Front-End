package com.phillies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.phillies.domain.Account;
import com.phillies.service.AccountService;

@Controller
public class LoginController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String index(Model model, Account account) {
		model.addAttribute("account", account);
		return "login";
	}
	
	@PostMapping("/login")
	public String processLogin(Model model, @RequestParam String username, @RequestParam String password) {
		Account account = (Account) getAccount(username, password);
		System.out.println(account.getFirstname());
		if (account==null)
			return "login";
		else {
			model.addAttribute("user", account);
		}
		loginDashboard(model, account);
		return "loginDashboard"; 
		
	}
	
	@RequestMapping(value="/loginDashboard", method=RequestMethod.GET)
	public String loginDashboard(Model model, Account account) {
		String logged = "Currently Logged in as: " + account.getFirstname() + " " + account.getLastname();
		model.addAttribute("currentlyLogged", logged);
//		model.addAttribute("firstName",account.getFirstname());
//		model.addAttribute("lastName",account.getLastname());
		System.out.println(account.getFirstname() + " " + account.getLastname());
		return "loginDashboard";
	}
	
	public Account getAccount(String username, String password) {
		Account temp = accountService.login(username, password);
		if(temp!=null) {
			
		}
		return temp;
	}
	
}
