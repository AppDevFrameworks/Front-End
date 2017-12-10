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
import java.text.DecimalFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.phillies.domain.FlowerPackage;
import com.phillies.domain.Order;
import com.phillies.entities.DataLoader;
import com.phillies.repository.FlowerPackageRepo;
import com.phillies.repository.FlowerRepo;
import com.phillies.service.FlowerPackageService;
import com.phillies.service.FlowerService;
import com.phillies.service.OrderService;

@Controller
public class MainController {

	@Autowired
	OrderService orderService;

	@Autowired
	FlowerPackageService flowerPackageService;

	@Autowired
	FlowerService flowerService;

	@Autowired
	FlowerRepo flowerRepo;

	@Autowired
	FlowerPackageRepo flowerPackageRepo;

	DataLoader dl = new DataLoader();

	String pk="", dt="", fn="", ln="", em="", mn="";
	float pr = 0;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model, Order order) {
		model.addAttribute("order", order);
		List<FlowerPackage> pack = flowerPackageService.getPackages();

		for(int i = 0; i < pack.size(); i++) {
			FlowerPackage p = pack.get(i);
			if(p.getStock() <= 0) {
				pack.remove(i);
			}
		}
		model.addAttribute("packages", pack);
		return "index";
	}

	@RequestMapping(value="/confirm", method=RequestMethod.POST)
	public String confirm(Order order) throws MalformedURLException, IOException {
		orderService.createOrder(new Order(orderService.getNextId(), fn, ln, em, mn, pk, dt, pr));
		String[] flowers = flowerPackageService.getFlowers(pk);
		for(String flower: flowers) {
			System.out.println(flower);
			flowerService.updateFlowerStock(flower, true, 5);
			if(flowerService.getStock(flower)<= 5) {
				String status = newOrder(flower,50);
				if(status.equals("{\"code\":1}")) {
					flowerService.updateFlowerStock(flower, false, 50);
				}
			}
		}
		return "confirm";
	}

	@RequestMapping(value="/order", method=RequestMethod.POST)
	public String orderSubmit(@Valid Order order, BindingResult bindingResult, @RequestParam String collection, @RequestParam String packages , Model model) throws Exception {
		if (bindingResult.hasFieldErrors()) {
			index(model, order);
			return "index";
		}

		String date = formatDate(collection);

		model.addAttribute("customer", order.getFirstName() + " " + order.getLastName());
		model.addAttribute("email", order.getEmailAddress());
		model.addAttribute("mobile", order.getMobileNo());
		model.addAttribute("package", packages);
		model.addAttribute("collection", date);		

		String itemString = itemsToString(packages);
		String flowerString = flowersToString(packages);
		FlowerPackage flowerPackage = flowerPackageService.getPackage(packages);

		pk = packages;
		dt = date;
		pr = flowerPackage.getPrice();
		fn = order.getFirstName();
		ln = order.getLastName();
		mn = order.getMobileNo();
		em = order.getEmailAddress();
		DecimalFormat df = new DecimalFormat("#.00");
		String angleFormated = df.format(flowerPackage.getPrice());

		model.addAttribute("flowers", flowerString);
		model.addAttribute("items", itemString);		
		model.addAttribute("price", "€" + angleFormated);

		return "orderReturn";
	}

	public String newOrder(String item, int amount) throws MalformedURLException, IOException {
		String url = "http://localhost:8090/getOrder?name=rob&pass=pass&item="+item+"-"+amount;
		String charset = "UTF-8";
		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true); // Triggers POST.
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
		String query = String.format("item=%s&amount=%s", 
				URLEncoder.encode(item, charset), 
				URLEncoder.encode(amount+"", charset));

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
		return result.toString();
	}

	public String formatDate(String d) {
		return d.substring(8,10) + " / " + d.substring(5, 7) + " / " + d.substring(0,4);
	}

	public String itemsToString(String packages) {
		String[] items = flowerPackageService.getItems(packages);
		String itemString = "";
		for(int i = 0; i < items.length; i++) {
			if(i != items.length-1)
				itemString += items[i] + ", ";
			else
				itemString += items[i];
		}
		return itemString;
	}

	public String flowersToString(String packages) {
		String[] flowers = flowerPackageService.getFlowers(packages);
		String flowerString = "";
		for(int i = 0; i < flowers.length; i++) {
			if(i != flowers.length-1)
				flowerString += flowers[i] + ", ";
			else
				flowerString += flowers[i];
		}
		return flowerString;
	}
}