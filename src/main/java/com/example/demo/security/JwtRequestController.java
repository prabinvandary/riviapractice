package com.example.demo.security;
import com.example.demo.model.JwtRequest;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin(origins = "*")
public class JwtRequestController {
    @Autowired
    AuthenticationProvider authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    Logger log= LoggerFactory.getLogger(this.getClass());

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResposnse> userToJwtToken(@RequestBody JwtRequest authenticationRequest) throws Exception{
        authenticateUser(authenticationRequest.getUserName(),authenticationRequest.getPassword());

        AasishDaiCustomUserDetails userDetails=(AasishDaiCustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String role=userDetails.getUser().getUserType().toString();
      return ResponseEntity.ok(new JwtResposnse(jwtTokenUtil.generateToken(userDetails),role));
    }

    private void authenticateUser(String userName,String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
    }
}
