package com.phillies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.phillies.domain.OrderForm;

public class OrderController {

	@Controller
	public class WebController extends WebMvcConfigurerAdapter {

//		@Override
//		public void addViewControllers(ViewControllerRegistry registry) {
//			registry.addViewController("/results").setViewName("results");
//		}

		@GetMapping("/getOrder")
		public String orderForm(Model model) {
			model.addAttribute("orderForm", new OrderForm());
			return "orderForm";
		}
	}
}
