package com.Banking.demo.controller;



import com.Banking.demo.Repository.RoleRepository;
import com.Banking.demo.Repository.UserRepository;
import com.Banking.demo.Security.JWTTokenProvider;
import com.Banking.demo.entity.Role;
import com.Banking.demo.entity.User;
import com.Banking.demo.payload.JwtAuthResponse;
import com.Banking.demo.payload.LoginDto;
import com.Banking.demo.payload.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse>  authenticateUser(@RequestBody LoginDto loginDto)
    {
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail()
        ,loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //get token from token provider class;

        String token= jwtTokenProvider.generateToken(authentication);


        return ResponseEntity.ok(new JwtAuthResponse(token));

        //return new ResponseEntity<>("user signed-in successfully!!!", HttpStatus.OK);
    }

    @PostMapping("/signup")
    private ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto)
    {
        //check usename exits in database

        if(userRepository.existsByUsername(signUpDto.getUsername()))
        {
            return new ResponseEntity<>("Username already exists", HttpStatus.OK);
        }

        if(userRepository.existsByEmail(signUpDto.getEmail()))
        {
            return new ResponseEntity<>("email already used",HttpStatus.OK);

        }

        User user=new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);


    }

}
