package com.example.BackendLearning.Controller;

import com.example.BackendLearning.Service.DashboardStatsService;
import com.example.BackendLearning.model.DashboardStats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardStatsController {

    private final DashboardStatsService service;

    public DashboardStatsController(DashboardStatsService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public DashboardStats getStats() {
        return service.getStats();
    }
}