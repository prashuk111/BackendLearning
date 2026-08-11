package com.example.BackendLearning.Service;

import com.example.BackendLearning.Exception.StudentNotFoundException;
import com.example.BackendLearning.Repository.JpaEntityRepository;
import com.example.BackendLearning.model.JpaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JpaEntityService {

    private final JpaEntityRepository repository;
    private final DashboardStatsService dashboardStatsService;

    public JpaEntityService(
            JpaEntityRepository repository,
            DashboardStatsService dashboardStatsService) {

        this.repository = repository;
        this.dashboardStatsService = dashboardStatsService;
    }

    // Get all students
    public List<JpaEntity> getAllStudents() {
        return repository.findAll();
    }

    // Find student by name
    public List<JpaEntity> findStudentByName(String name) {
        return repository.findByName(name);
    }

    // Get student by ID
    public JpaEntity getStudentById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(id)
                );
    }

    // ADD STUDENT
    @Transactional
    public JpaEntity saveStudent(JpaEntity student) {

        JpaEntity savedStudent = repository.save(student);

        dashboardStatsService.incrementAdded();

        return savedStudent;
    }

    // UPDATE STUDENT
    @Transactional
    public JpaEntity updateStudent(Long id, JpaEntity updatedStudent) {

        JpaEntity existingStudent = repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(id)
                );

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setCourse(updatedStudent.getCourse());

        return repository.save(existingStudent);
    }

    // DELETE STUDENT
    @Transactional
    public void deleteStudent(Long id) {

        JpaEntity student = repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(id)
                );

        repository.delete(student);
    }

    // Delete by name
    @Transactional
    public void deleteByStudentName(String name) {

        repository.deleteByName(name);

        dashboardStatsService.incrementDeleted();
    }
}