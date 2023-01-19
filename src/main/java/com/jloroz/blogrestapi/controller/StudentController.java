package com.jloroz.blogrestapi.controller;

import com.jloroz.blogrestapi.bean.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    Logger log = LoggerFactory.getLogger(StudentController.class);

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

    @GetMapping("/student/{id}")
    public ResponseEntity studentPathVariable(@PathVariable("id") int studentId){

        return ResponseEntity.ok(new Student(studentId,"name-"+studentId,"lastname-"+studentId));
    }
    @GetMapping("/student/{id}/{first-name}/{last-name}")
    public ResponseEntity studentMultiplePathVariables(@PathVariable("id") int studentId,
                                                       @PathVariable("first-name") String firstName,
                                                       @PathVariable("last-name") String lastName){

        return ResponseEntity.ok(new Student(studentId,firstName,lastName));
    }

    @GetMapping("/student/query")
    public ResponseEntity studentRequestParam(@RequestParam("id") int studentId,
                                                       @RequestParam("first-name") String firstName,
                                                       @RequestParam("last-name") String lastName){

        return ResponseEntity.ok(new Student(studentId,firstName,lastName));
    }

    @PostMapping("/student/create")
    public ResponseEntity studentCreate(@RequestBody Student student){
        log.info(student.toString());
        return new ResponseEntity(student, HttpStatus.CREATED);
    }

}
