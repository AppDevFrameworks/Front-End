package com.phillies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phillies.domain.Flower;
import com.phillies.domain.FlowerPackage;
import com.phillies.domain.Order;
import com.phillies.repository.FlowerPackageRepo;
import com.phillies.repository.FlowerRepo;
import com.phillies.repository.OrderRepo;
import com.phillies.service.OrderService;

@Controller
public class ManagementController {

	@Autowired
	private FlowerRepo flowerRepo;
	
	@Autowired
	private FlowerPackageRepo flowerPackageRepo;
	
	@Autowired
	private OrderRepo orderRepo;

	@RequestMapping(value="/stock", method=RequestMethod.GET)
	public String stock(Model model) {
		List<Flower> flowers =  flowerRepo.findAll();
		List<FlowerPackage> packages = flowerPackageRepo.findAll();
		
		model.addAttribute("flowers", flowers);
		model.addAttribute("packages", packages);
		return "stock";
	}
	
	@RequestMapping(value="/orderManage", method=RequestMethod.GET)
	public String order(Model model) {
		List<Order> orders = orderRepo.findAll();
		model.addAttribute("orders",orders);
		return "orderManagement";
	}
}
