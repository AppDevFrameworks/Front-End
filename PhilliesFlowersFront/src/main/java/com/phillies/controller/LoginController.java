package com.phillies.controller;

import java.text.DecimalFormat;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.phillies.domain.Account;
import com.phillies.domain.Order;
import com.phillies.service.AccountService;
import com.phillies.service.OrderService;

@Controller
public class LoginController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OrderService orderService;


	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String index(Model model, Account account, HttpSession session) {
		if(session.getAttribute("user")==null) {
			return "login";
		}
		model.addAttribute("account", account);
		return "login";
	}

	@PostMapping("/login")
	public String processLogin(@Valid Account account, BindingResult bindingResult, Model model, @RequestParam String username, @RequestParam String password, HttpSession session) {
		account = (Account) getAccount(username, password);
		if (account == null) {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
		else {
			model.addAttribute("user", account);
		}
		loginDashboard(model, account);
		return "loginDashboard"; 

	}

	@RequestMapping(value="/loginDashboard", method=RequestMethod.GET)
	public String loginDashboard(Model model, Account account) {
		String logged = "Currently Logged in as: " + account.getFirstname() + " " + account.getLastname();	
		Order o = orderService.getLastOrder();
		
		if(o != null) {
			model.addAttribute("title", "Most Recent Order");
			model.addAttribute("nameTitle", "Customer Name:");
			model.addAttribute("packageTitle", "Package:");
			model.addAttribute("priceTitle", "Package Price:");
			model.addAttribute("dateTitle", "Collection Date:");

			DecimalFormat df = new DecimalFormat("#.00");
		    String angleFormated = df.format(o.getPrice());
		    model.addAttribute("currentlyLogged", logged);	
			model.addAttribute("name", o.getFirstName() + " " + o.getLastName());
			model.addAttribute("package", o.getPackageName());			
			model.addAttribute("price", "€" + angleFormated);
			model.addAttribute("date", o.getDate());
		}
		else 
			model.addAttribute("title", "No Orders have been made yet!");
		return "loginDashboard";
	}

	public Account getAccount(String username, String password) {
		Account temp = accountService.login(username, password);
		if(temp!=null) {
		}
		return temp;
	}
}
