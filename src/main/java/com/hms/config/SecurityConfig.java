
package com.hms.config;

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

import com.hms.exceptions.MyAuthenticationFailureHandler;
import com.hms.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

	@Bean
	public DaoAuthenticationProvider authProvider() throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsServiceBean());
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authProvider()).build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((athReqs) -> athReqs.requestMatchers("/signin**", "/register**", "/about/**",
				"/js/**", "/css/**", "/img/**", "/resources/**","/static/**","**.jpg","/userRestrictedPage**").permitAll()
				.requestMatchers("/home","/profile","/search","/bookings/**","/manage/users/inactivateUser").authenticated()
				 .requestMatchers("/manage/**").hasAuthority("ADMIN") 
				).httpBasic().disable().formLogin()
				.loginPage("/signin").loginProcessingUrl("/process-signin").defaultSuccessUrl("/home").permitAll()
				.failureHandler(new MyAuthenticationFailureHandler()).and()
				.authenticationManager(authManager(http)).csrf().disable()
				.exceptionHandling().accessDeniedPage("/userRestrictedPage")
				 ;
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new UserServiceImpl();
	}

}
