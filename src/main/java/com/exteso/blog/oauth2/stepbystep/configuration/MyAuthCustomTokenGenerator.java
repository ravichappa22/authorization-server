package com.exteso.blog.oauth2.stepbystep.configuration;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;

@Component
public class MyAuthCustomTokenGenerator {
	
	@Autowired
	private AuthorizationServerEndpointsConfiguration configuration;

	
	public OAuth2AccessToken generateOAuth2AccessToken(User user, List<GrantedAuthority> authorities) {

	    Map<String, String> requestParameters = new HashMap<String, String>();
	    Map<String, Serializable> extensionProperties = new HashMap<String, Serializable>();

	    boolean approved = true;
	    Set<String> responseTypes = new HashSet<String>();
	    responseTypes.add("code");
	    
	    OAuth2Request oauth2Request = new OAuth2Request(requestParameters, user.getUsername(), authorities, approved, null, null, null, responseTypes, extensionProperties);

	    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, "N/A", authorities);

	    OAuth2Authentication auth = new OAuth2Authentication(oauth2Request, authenticationToken);

	    AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();

	   // OAuth2AccessToken token = 
	    	return	tokenService.createAccessToken(auth);

	  //  return token.getValue();
	}

}
