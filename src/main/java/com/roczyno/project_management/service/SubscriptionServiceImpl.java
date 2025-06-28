package com.roczyno.project_management.service;

import com.roczyno.project_management.model.PlanType;
import com.roczyno.project_management.model.Subscription;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setSubscriptionEndDate(LocalDate.now().plusDays(12));
        subscription.setValid(true);
        subscription.setPlanType(PlanType.FREE);

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUserSubscription(Long userId) {


       Subscription subscription=subscriptionRepository.findByUserId(userId);
       if(!subscription.isValid()){
           subscription.setPlanType(PlanType.FREE);
           subscription.setSubscriptionStartDate(LocalDate.now());
           subscription.setSubscriptionEndDate(LocalDate.now().plusDays(12));
       }
       return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgradeSubscription(Long userId, PlanType planType) {
        Subscription subscription=getUserSubscription(userId);
        subscription.setPlanType(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        if(planType.equals(PlanType.ANNUALLY)) {
            subscription.setSubscriptionEndDate(LocalDate.now().plusDays(12));
        }
        else {
            subscription.setSubscriptionEndDate(LocalDate.now().plusDays(1));
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValidSubscription(Subscription subscription) {
        if(subscription.getPlanType().equals(PlanType.FREE)) {
            return true;
        }
        LocalDate endDate=subscription.getSubscriptionEndDate();
        LocalDate currentDate=LocalDate.now();

        return endDate.isAfter(currentDate)||endDate.isEqual(currentDate);
    }
}
