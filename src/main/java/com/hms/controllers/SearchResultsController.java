/*
 * package com.hms.controllers;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute;
 * 
 * import com.hms.dto.SearchParameterDTO; import
 * com.hms.repositories.RoomDefinitionRepository;
 * 
 * import jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpSession;
 * 
 * @Controller public class SearchResultsController {
 * 
 * @Autowired RoomDefinitionRepository roomDefinitionRepository;
 * 
 * @GetMapping("/results") public String
 * showResultsPage(@ModelAttribute("search") SearchParameterDTO search, Model
 * model) {
 * System.out.println("SearchParameterDTO object in GET method of results " +
 * search.toString()); model.addAttribute("rooms",
 * roomDefinitionRepository.findByRoomCode("VIL")); return "results"; } }
 */