package com.hms.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hms.dto.SearchParameterDTO;
import com.hms.services.SearchService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	public ModelAndView processSearch(@ModelAttribute("search") @Valid SearchParameterDTO search, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, Errors errors, Model model) throws IOException {
		ModelAndView mav=  new ModelAndView();
		model.addAttribute("roomTypes", searchService.fetchRoomDefinitions());
		System.out.println("SearchParameterDTO object in POST method of search " + search.toString());
		if (result.hasErrors()) {
			mav.setViewName("search");
			mav.addObject("roomTypes", searchService.fetchRoomDefinitions());
			return mav;
		} else {
			mav.setViewName("results");
			mav.addObject("roomTypes", searchService.fetchRoomDefinitions());
			if (!search.getPreferredRoomType().equals("")) {
				System.out.println("getPreferredRoomType is not null");
				mav.addObject("roomDto", searchService.setRoomDto(search.getPreferredRoomType()));
			} else {
				mav.addObject("roomDto", searchService.setRoomDtoAll());
			}
			mav.addObject("search", search);
	        return mav;
		}
	}

}
