package com.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {

	@GetMapping("/userRestrictedPage")
	public String getAccessDenied() {
		return "userRestrictedPage";
	}
}