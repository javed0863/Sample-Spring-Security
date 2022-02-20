package com.javedrpi.springsecurityoauth2new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringSecurityOauth2NewApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOauth2NewApplication.class, args);
	}

	@GetMapping("/")
	public String home(){
		return "Hello from new security app :D";
	}

	@GetMapping("/secured")
	public String secured(){
		return "Hello from Secured-Page !!!";
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.logout(l -> l.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID"))
				.authorizeRequests(a -> a
						.antMatchers("/").permitAll()
						.anyRequest().authenticated()
				)
				// This will ask user to authenticate if secured endpoint is accessed without completing authentication
				.oauth2Login()
				// Unblocking below block will throw HTTP 401 if a secured endpoint is accessed without completing authentication
				/*.exceptionHandling(e -> e
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				)
				.oauth2Login()*/
		;

		// Another way of setting restrictions
		/*http
				.logout(l -> l.logoutSuccessUrl("/").permitAll())
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.oauth2Login();*/
	}
}
