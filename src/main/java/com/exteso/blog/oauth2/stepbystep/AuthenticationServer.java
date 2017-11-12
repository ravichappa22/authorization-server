package com.exteso.blog.oauth2.stepbystep;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exteso.blog.oauth2.stepbystep.configuration.MyAuthCustomTokenGenerator;

@SpringBootApplication
@RestController
@EnableResourceServer
public class AuthenticationServer{
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServer.class, args);
    }

    private static final Log logger = LogFactory.getLog(AuthenticationServer.class);
    
    @Autowired
    private MyAuthCustomTokenGenerator myAuthCustomTokenGenerator;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        logger.info("AS /user has been called");
        logger.debug("user info: " + user.toString());
        return user;
    }
    
    @RequestMapping(value="/getTokenForUser", method=RequestMethod.GET)
    public OAuth2AccessToken userToken(@RequestHeader String roles, @RequestHeader String userName) {
        logger.info("AS /userToken has been called and the Roles are =" + roles);
        //Assume we got privileges from DB whic is ROLE_READ, ROLE_WRITE
        // Authorities
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    for(String role: roles.split(","))
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        User user = new User(userName, "N/A" , authorities);
         return myAuthCustomTokenGenerator.generateOAuth2AccessToken(user, authorities);
      //  logger.debug("user info: " + user.toString());
       // return user;
       // return roles;
    }
}
