package org.example.crudapplication.controller;

import org.example.crudapplication.entity.Student;
import org.example.crudapplication.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService ;

    public StudentController(StudentService studentService){
        this.studentService = studentService ;
    }

    // Create student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
//        System.out.println("Inside Student Controller ..");

        Student createdStudent = studentService.createStudent(student) ;
//        System.out.println("Exiting Student Controller ..");
        return ResponseEntity.status(201).body(createdStudent) ;
    }

    // Read student
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student studentResp =  studentService.getStudent(id) ;
        if(studentResp == null) {
            return ResponseEntity.notFound().build() ;
        }
        return ResponseEntity.ok(studentResp) ;
    }


    // Update student
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id , @RequestBody Student studentReq) {
        Student studentResp =  studentService.updateStudent(id , studentReq) ;
        if(studentResp == null) {
            return ResponseEntity.notFound().build() ;
        }
        return ResponseEntity.ok(studentResp) ;
    }

    // Delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id ) {
        Boolean isDeleted = studentService.deleteStudent(id) ;

        if(!isDeleted) {
            return ResponseEntity.notFound().build() ;
        }

        return ResponseEntity.ok("Record Deleted") ;
    }

    // Read all student
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList =  studentService.getAllStudent() ;
        if(studentList.isEmpty()) {
            return ResponseEntity.notFound().build() ;
        }
        return ResponseEntity.ok(studentList) ;
    }

    // Soft Delete
    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deletedStudentSoftly(@PathVariable long id ) {
        Boolean isDeleted = studentService.deleteStudentSoftly(id) ;

        if(!isDeleted) {
            return ResponseEntity.notFound().build() ;
        }

        return ResponseEntity.ok("Record deleted.") ;
    }
}
