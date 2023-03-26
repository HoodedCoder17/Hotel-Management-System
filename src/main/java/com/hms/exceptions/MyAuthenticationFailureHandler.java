package com.hms.exceptions;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//super.onAuthenticationFailure(request, response, exception);
		
		System.out.println("A failed login attempt, Reason: " + exception.getMessage());
		request.setAttribute("errormsg", exception.getMessage());
		String redirectUrl = request.getContextPath() + "/signin?error=true";
        response.sendRedirect(redirectUrl);
	}
}
