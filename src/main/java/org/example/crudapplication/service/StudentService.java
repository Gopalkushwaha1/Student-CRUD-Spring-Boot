package org.example.crudapplication.service;

import org.example.crudapplication.entity.Student;
import org.example.crudapplication.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository ;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository ;
    }

    // 1. EndPoint listen ( api/student )

    public Student createStudent(Student studentReq) {
        // Business Login
        // store to db or call to repo
        // Validation check here
//        System.out.println("Inside Student Service..");
        studentReq.setDeleted(false);
        Student studentResp =  studentRepository.save(studentReq) ;
//        System.out.println("Exiting  Student Service..");
        return studentResp ;
    }

    public Student getStudent(Long id ) {
        Optional<Student> studentResp =  studentRepository.findByIdAndDeletedIsFalse(id) ;

        if ( studentResp.isPresent()) {
            return studentResp.get() ;
        }
        return null ;

    }

    public List<Student> getAllStudent() {

        List<Student> studentsList =  studentRepository.findByDeletedIsFalse() ;

        return studentsList ;
    }

    public Student updateStudent(Long id  , Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id) ;
        if(existingStudent.isEmpty()) {
            return null ;
        }

        Student studentToSave = existingStudent.get() ;

        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setAge(studentReq.getAge());

        studentToSave.setDeleted(false);
        return studentRepository.save(studentToSave) ;
    }


    public Boolean deleteStudent(Long id ) {

        Boolean isStudent = studentRepository.existsById(id) ;
        if(!isStudent) return false ;

        studentRepository.deleteById(id);

        return true ;
    }

    public Boolean deleteStudentSoftly(long id) {
        Optional<Student> student = studentRepository.findByIdAndDeletedIsFalse(id) ;

        if(student.isEmpty()) {
            return false ;
        }

        Student studentToSave =  student.get() ;
        studentToSave.setDeleted(true);

        studentRepository.save(studentToSave) ;

        return true ;
    }


    // 2. Business Logic

    // 3. Interact with DB to store

    // 4. Response back to client

}
