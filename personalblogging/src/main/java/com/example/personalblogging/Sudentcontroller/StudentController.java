package com.example.personalblogging.Sudentcontroller;

import com.example.personalblogging.Repository.Repository;
import com.example.personalblogging.entity.Student;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    Repository repo;
   // to get all the students
    //localhost:8080/Students
    @GetMapping("/Students")
    public List<Student>getAllStudent(){
        List<Student> Student = repo.findAll();
        return Student;
    }

    @GetMapping("/Students/{id}")
    public Student getStudent(@PathVariable int id){
        Student student = repo.findById(id).get();

        return student;
    }
    @PostMapping("/student/update/{id}")
    public Student updateStudents(@PathVariable int id,@RequestBody Student updatedStudent){
        Student student = repo.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        student.setName(updatedStudent.getName());
        student.setPercentage(updatedStudent.getPercentage());
        student.setBranch(updatedStudent.getBranch());
        student.setSection(updatedStudent.getSection());
        return repo.save(student);
    }
    @PostMapping("/student/add")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student newStudent = repo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }
    @DeleteMapping("/student/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeStudent(@PathVariable int id){
        Student student = repo.findById(id).get();
        repo.delete(student);
    }





}
