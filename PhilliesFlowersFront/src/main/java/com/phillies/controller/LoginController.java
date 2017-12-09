package com.phillies.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.phillies.domain.Account;
import com.phillies.domain.Order;
import com.phillies.repository.OrderRepo;
import com.phillies.service.AccountService;

@Controller
public class LoginController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private OrderRepo orderRepo;

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
		int lastIndex = (int) (orderRepo.count()-1);

		if(lastIndex != -1) {
			Order lastOrder = orderRepo.findAll().get(lastIndex);
			System.out.println(lastOrder.getPackageName());
			String name = lastOrder.getFirstName() + " " + lastOrder.getLastName();
			model.addAttribute("nameTitle", "Customer Name:");
			model.addAttribute("name", name);
			model.addAttribute("packageTitle", "Package:");
			model.addAttribute("package", lastOrder.getPackageName());
			model.addAttribute("priceTitle", "Package Price:");
			
			DecimalFormat df = new DecimalFormat("#.00");
		    String angleFormated = df.format(lastOrder.getPrice());
			
			model.addAttribute("price", "€" + angleFormated);
			model.addAttribute("dateTitle", "Collection Date:");
			model.addAttribute("date", lastOrder.getDate());
		}
		else {
			model.addAttribute("error", "No Orders have been made yet!");
		}
		return "loginDashboard";
	}

	public Account getAccount(String username, String password) {
		Account temp = accountService.login(username, password);
		if(temp!=null) {

		}
		return temp;
	}

}
