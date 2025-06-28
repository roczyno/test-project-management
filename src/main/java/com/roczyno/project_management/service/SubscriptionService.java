package com.roczyno.project_management.service;

import com.roczyno.project_management.model.PlanType;
import com.roczyno.project_management.model.Subscription;
import com.roczyno.project_management.model.User;

public interface SubscriptionService {
    Subscription createSubscription(User user);
    Subscription getUserSubscription(Long userId);
    Subscription upgradeSubscription(Long userId, PlanType planType);
    boolean isValidSubscription(Subscription subscription);
}
