package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {
	
	@RequestMapping("/viral")
	public String index(){
		return "viral";
	}
	
	@RequestMapping("challenge")
	public String challange (@RequestParam(value="name") String name, Model model) {
		model.addAttribute("name",name);
		return "challenge";
	}
	
	@RequestMapping("/challenge/{name}")
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		} else {
			model.addAttribute("name","KB");
		}
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String generator (@RequestParam(value = "a") int a, @RequestParam(value = "b") int b, Model model ) {	
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		String m = "m";
		if(a>0) {
			m = StringUtils.repeat(m, a);
		}
		String hm = "h" + m + " ";
		if(b>0) {
			hm = StringUtils.repeat(hm, b);
		}
		model.addAttribute("hm",hm);
		return "generator";
		
	}
}
