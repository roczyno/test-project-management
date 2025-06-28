package com.roczyno.project_management.controller;

import com.roczyno.project_management.model.User;
import com.roczyno.project_management.request.LoginRequest;
import com.roczyno.project_management.service.AuthenticationService;
import com.roczyno.project_management.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User userReq) {
        return ResponseHandler.successResponse(authenticationService.registerUser(userReq),HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest userRequest) {
        return ResponseHandler.successResponse(authenticationService.login(userRequest), HttpStatus.OK);

    }
}
