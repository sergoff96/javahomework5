package org.example.service;

import org.example.entity.Limit;
import org.example.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LimitService {

    private final LimitRepository limitRepository;

    @Autowired
    public LimitService(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    public Limit getOrCreateLimit(Long userId) {
        return limitRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Limit newLimit = new Limit(userId, BigDecimal.valueOf(10000.00));
                    return limitRepository.save(newLimit);
                });
    }

    public void deductLimit(Long userId, BigDecimal amount) {
        Limit limit = getOrCreateLimit(userId);
        if (limit.getRemainingLimit().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient daily limit");
        }
        limit.setRemainingLimit(limit.getRemainingLimit().subtract(amount));
        limitRepository.save(limit);
    }

    public void restoreLimit(Long userId, BigDecimal amount) {
        Limit limit = getOrCreateLimit(userId);
        limit.setRemainingLimit(limit.getRemainingLimit().add(amount));
        if (limit.getRemainingLimit().compareTo(limit.getDailyLimit()) > 0) {
            limit.setRemainingLimit(limit.getDailyLimit());
        }
        limitRepository.save(limit);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDailyLimits() {
        List<Limit> limits = limitRepository.findAll();
        for (Limit limit : limits) {
            limit.setRemainingLimit(limit.getDailyLimit());
        }
        limitRepository.saveAll(limits);
    }
}

