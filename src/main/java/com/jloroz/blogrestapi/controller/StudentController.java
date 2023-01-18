package com.jloroz.blogrestapi.controller;

import com.jloroz.blogrestapi.bean.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/student")
    public ResponseEntity getStudent(){
        return ResponseEntity.ok(new Student(1, "Luca", "Lobo"));
    }

    @GetMapping("/students")
    public ResponseEntity getStudents(){
        List students = List.of(new Student(1, "Luca", "Lobo"),
                new Student(2, "Luis", "Oro"));

        return ResponseEntity.ok(students);
    }
}
