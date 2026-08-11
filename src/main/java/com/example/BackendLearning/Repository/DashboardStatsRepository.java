package com.example.BackendLearning.Repository;

import com.example.BackendLearning.model.DashboardStats;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface DashboardStatsRepository
            extends JpaRepository<DashboardStats, Long> {
    }

