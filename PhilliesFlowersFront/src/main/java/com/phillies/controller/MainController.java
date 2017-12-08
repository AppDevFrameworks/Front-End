package com.phillies.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties.Packages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.phillies.domain.Account;
import com.phillies.domain.Flower;
import com.phillies.domain.FlowerPackage;
import com.phillies.domain.Order;
import com.phillies.entities.DataLoader;
import com.phillies.domain.Account;
import com.phillies.repository.FlowerRepo;
import com.phillies.repository.OrderRepo;
import com.phillies.repository.PackageRepo;

@Controller
public class MainController {

	@Autowired
	FlowerRepo flowerRepo;
	
	@Autowired
	PackageRepo packageRepo;
	
	@Autowired
	OrderRepo orderRepo;
	
	DataLoader dl = new DataLoader();
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model, Order order) {
		model.addAttribute("order", order);
		List<FlowerPackage> packages =  packageRepo.findAll();
		model.addAttribute("packages", packages);
		return "index";
	}
	
	@RequestMapping(value="/order", method=RequestMethod.POST)
	public String orderSubmit(Order order, @RequestParam String packages , BindingResult bindingResult, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		model.addAttribute("customer", "Customer Name: " + order.getFirstName() + " " + order.getLastName());
		model.addAttribute("email", "Customer E-mail Address: " + order.getEmailAddress());
		model.addAttribute("mobile", "Customer Phone Number: " + order.getMobileNo());
		model.addAttribute("package", "Package Chosen: " + packages);
		
		String flowers = "Flowers Included: ";
		float price = 0;
		String item = "Items Included: ";
		List<FlowerPackage> packs =  packageRepo.findAll();
		for(FlowerPackage fp: packs) {
			if(fp.getName().equals(packages)) {
				price = fp.getPrice();
				for(String f : fp.getItems()) {
					item += f + " ";
				}
				for(String f : fp.getFlowers()) {
					flowers += f + " ";
				}
			}
		}
		
		model.addAttribute("flowers", flowers);
		model.addAttribute("items", item);		
		model.addAttribute("price", "Price: €" + price);
		
		int id = (int) orderRepo.count();
		orderRepo.save(new Order(id, order.getFirstName(), order.getLastName(), 
				order.getEmailAddress(), order.getMobileNo(), packages, price));
		
		//model.addAttribute("status", orderMore("Red Flowers", 1000));
		return "orderReturn";
	}
	
	public String orderMore(String item, int amount) throws MalformedURLException, IOException {
		String url = "http://localhost:8090/getOrder";
		String charset = "UTF-8";
		String query = String.format("item=%s&amount=%s", 
			     URLEncoder.encode(item, charset), 
			     URLEncoder.encode(amount+"", charset));
		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true); // Triggers POST.
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

		try (OutputStream output = connection.getOutputStream()) {
		    output.write(query.getBytes(charset));
		}

		InputStream response = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(response));
		StringBuilder result = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
		    result.append(line);
		}
		System.out.println(result.toString());
		return result.toString();
	}
}