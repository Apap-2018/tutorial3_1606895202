package com.apap.tutorial3.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.apap.tutorial3.PilotModel;
import com.apap.tutorial3.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "licenseNumber", required = true) String licenseNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		model.addAttribute("listPilot",archive);
		return "viewall-pilot";
	}
	
	@RequestMapping("/pilot/view/license-number/{licenseNumber}")
	public String viewPilotWithLicenseNumber(@PathVariable String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		if(archive == null) {
			System.out.println("halooo");
			return "redirect:/errorView";
		}else {
			
			model.addAttribute("pilot", archive);
			return "view-pilot";
		}
		
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}")
	public String updatePilotWithLicenseNumber(@PathVariable String licenseNumber,@PathVariable int flyHour, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(archive == null) {
			return "redirect:/errorView";
		}else {
			archive.setFlyHour(flyHour);
			model.addAttribute("pilot", archive);
			return "redirect:/change";
		}
		
	}
	
	@RequestMapping("/pilot/delete/id/{id}")
	public String deletePilotWithLicenseNumber(@PathVariable String id, Model model) {
		PilotModel archive = pilotService.deletePilotDetailById(id); // mendapat object pilot dengan id, method ada di pilotservice
		if(archive == null) {
			return "redirect:/errorDelete";
		}else {
			return "redirect:/delete";
		}
		
	}
	
	@RequestMapping("/errorView")
	public String error() {
		return "errorView";
	}
	
	@RequestMapping("/change")
	public String change() {
		return "change";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		return "delete";
	}
	
	
}
