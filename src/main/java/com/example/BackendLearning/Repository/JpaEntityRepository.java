package com.example.BackendLearning.Repository;
import com.example.BackendLearning.model.JpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEntityRepository extends JpaRepository<JpaEntity, Long>{
        void deleteByName(String name);
        List<JpaEntity> findByName(String name);
}
