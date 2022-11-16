//package com.example.demo.Controller;
//
//import com.example.demo.pojo.ApiResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthenticationController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
//       try {
//           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName),authenticationRequest.getPassword);
//       }
//       catch (BadCredentialsException e){
//           throw new Exception("Incorrect Username or password",e);
//       }
//    }
//
//}