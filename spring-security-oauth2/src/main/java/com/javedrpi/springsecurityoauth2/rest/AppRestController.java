package com.javedrpi.springsecurityoauth2.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {

    @RequestMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Hello World :D");
    }

    @RequestMapping("/app")
    public ResponseEntity<String> app(Authentication authentication){
        return ResponseEntity.ok("App name: Spring-Security-Oauth2 - "+authentication.toString());
    }

    @RequestMapping("/desc")
    public ResponseEntity<String> desc(){
        return ResponseEntity.ok("This App is used to explain Oauth2 integration with springboot");
    }
}
