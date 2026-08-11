package com.example.BackendLearning.Service;

import com.example.BackendLearning.Repository.DashboardStatsRepository;
import com.example.BackendLearning.model.DashboardStats;
import org.springframework.stereotype.Service;

@Service
public class DashboardStatsService {

    private final DashboardStatsRepository repository;

    public DashboardStatsService(DashboardStatsRepository repository) {
        this.repository = repository;
    }

    public DashboardStats getStats() {

        return repository.findById(1L)
                .orElseGet(() -> {

                    DashboardStats stats = new DashboardStats();

                    stats.setAddedCount(0);
                    stats.setUpdatedCount(0);
                    stats.setDeletedCount(0);

                    return repository.save(stats);
                });
    }

    public void incrementAdded() {
        DashboardStats stats = getStats();
        stats.setAddedCount(stats.getAddedCount() + 1);
        repository.save(stats);
    }

    public void incrementUpdated() {
        DashboardStats stats = getStats();
        stats.setUpdatedCount(stats.getUpdatedCount() + 1);
        repository.save(stats);
    }

    public void incrementDeleted() {
        DashboardStats stats = getStats();
        stats.setDeletedCount(stats.getDeletedCount() + 1);
        repository.save(stats);
    }
}