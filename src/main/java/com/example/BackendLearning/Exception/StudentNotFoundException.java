package com.example.BackendLearning.Exception;

    public class StudentNotFoundException extends RuntimeException {

        public StudentNotFoundException(Long id) {
            super("Student with id " + id + " not found");
        }
    }

