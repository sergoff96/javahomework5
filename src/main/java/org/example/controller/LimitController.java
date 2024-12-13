package org.example.controller;

import org.example.entity.Limit;
import org.example.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/limits")
public class LimitController {

    private final LimitService limitService;

    @Autowired
    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Limit> getLimit(@PathVariable Long userId) {
        Limit limit = limitService.getOrCreateLimit(userId);
        return ResponseEntity.ok(limit);
    }

    @PostMapping("/deduct")
    public ResponseEntity<String> deductLimit(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        try {
            limitService.deductLimit(userId, amount);
            return ResponseEntity.ok("Limit deducted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/restore")
    public ResponseEntity<String> restoreLimit(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        limitService.restoreLimit(userId, amount);
        return ResponseEntity.ok("Limit restored successfully");
    }
}

