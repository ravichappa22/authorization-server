package com.exteso.blog.oauth2.stepbystep.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;


@Configuration
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {
	
	/*@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("oauth/token","/oauth/check_token","oauth/token_key","/userToken");
		
	}*/

	@Override
	public void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests().antMatchers("/login","oauth/token","oauth/check_token","auth/check_token", "oauth/token_key", "oauth/validate","oauth/validate", "/getTokenForUser")
						.permitAll().anyRequest().authenticated();
	}
	
	@Bean
	public HttpSessionSecurityContextRepository contextRepository(){
		return new HttpSessionSecurityContextRepository();
	}
	
	
	
	

}
