package com.example.BackendLearning.Controller;

import com.example.BackendLearning.Service.JpaEntityService;
import com.example.BackendLearning.model.JpaEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/students")
public class JpaEntityController {

    private final JpaEntityService service;

    public JpaEntityController(JpaEntityService service) {
        this.service = service;
    }

    @GetMapping
    public List<JpaEntity> getAllStudents() {
        return service.getAllStudents();
    }
    @GetMapping("/name/{name}")
    public List<JpaEntity> findStudentByName(@PathVariable String name){
        return service.findStudentByName(name);
    }
    @GetMapping("/{id}")
    public JpaEntity getStudent(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @PostMapping
    public JpaEntity addStudent(
            @Valid @RequestBody JpaEntity student) {

        return service.saveStudent(student);
    }

    @PutMapping("/{id}")
    public JpaEntity updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody JpaEntity student) {

        return service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }

    @DeleteMapping("/name/{name}")
    public void deleteByStudentName(@PathVariable String name) {
        service.deleteByStudentName(name);
    }
}
