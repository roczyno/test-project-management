package com.roczyno.project_management.repository;

import com.roczyno.project_management.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    Subscription findByUserId(Long userId);
}
