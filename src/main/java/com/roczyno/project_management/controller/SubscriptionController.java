package com.roczyno.project_management.controller;

import com.roczyno.project_management.model.PlanType;
import com.roczyno.project_management.model.Subscription;
import com.roczyno.project_management.service.SubscriptionService;
import com.roczyno.project_management.service.UserService;
import com.roczyno.project_management.util.ResponseHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Subscription> create(@RequestHeader("Authorization") String jwt){
        Subscription subscription=subscriptionService.createSubscription(userService.findUserProfileByJwt(jwt));
        return ResponseEntity.ok(subscription);
    }
    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(subscriptionService.getUserSubscription(userService.findUserProfileByJwt(jwt).getId()),
                HttpStatus.OK);
    }
    @GetMapping("/user/upgrade")
    public ResponseEntity<Object> upgradeUserSubscription(@RequestHeader("Authorization") String jwt,
                                                          @RequestParam PlanType planType){
        return ResponseHandler.successResponse(subscriptionService.upgradeSubscription(
                userService.findUserProfileByJwt(jwt).getId(),
                planType),HttpStatus.OK);
    }



}
