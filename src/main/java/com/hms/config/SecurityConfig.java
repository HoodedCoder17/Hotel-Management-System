
package com.hms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.hms.services.UserService;
import com.hms.services.UserServiceImpl;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

	/*
	 * @Autowired private UserService userService;
	 */

	@PostConstruct
	public void postConstruct() {
		System.out.println("SecurityConfig bean has been constructed");
	}

	@Bean
	public DaoAuthenticationProvider authProvider() throws Exception {
		System.out.println("In security config 1");
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		/* authProvider.setUserDetailsService((UserDetailsService) userService); */
		authProvider.setUserDetailsService(userDetailsServiceBean());
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		System.out.println("In security config 2");
		return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authProvider()).build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(athReqs) -> athReqs.requestMatchers("/signin**", "/register**", "/js/**", "/css/**", "/img/**")
						.permitAll().requestMatchers("/home")
						.authenticated().requestMatchers("/rooms").hasAnyAuthority("ADMIN","USER")/*.requestMatchers("/**").hasAuthority("USER") */)
				 .httpBasic().disable().formLogin().loginPage("/signin").loginProcessingUrl("/process-signin")
				.defaultSuccessUrl("/home").failureUrl("/signinFailure").permitAll().and()

				.authenticationManager(authManager(http))
		 .csrf().disable() ;
		System.out.println("In security config");
		return http.build();
	}

	/*
	 * public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * System.out.println("In security config 3");
	 * registry.addResourceHandler("/resources/**").addResourceLocations(
	 * "/resources/"); }
	 */
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new UserServiceImpl();
	}

}
