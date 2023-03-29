package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hms.dto.SearchParameterDTO;
import com.hms.services.SearchService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class SearchController {

	@Autowired
	SearchService searchService;

	@GetMapping("/search")
	public String showSearchPage(HttpServletRequest request, HttpSession session, Model model) {
		model.addAttribute("roomTypes", searchService.fetchRoomDefinitions());
		model.addAttribute("search", new SearchParameterDTO());
		return "search";
	}

	@PostMapping("/search")
	public String processSearch(@ModelAttribute("search") @Valid SearchParameterDTO search, BindingResult result,
			HttpServletRequest request, Errors errors,Model model) {
		model.addAttribute("roomTypes", searchService.fetchRoomDefinitions());
		System.out.println(search.toString());
		if(result.hasErrors()) {
			return "search";
		}
		return null;
	}

}
