package six.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import six.lab.entity.Student;
import six.lab.response.CustomResponse;
import six.lab.service.StudentService;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public CustomResponse<List<Student>> allStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public CustomResponse<Student> getStudent(@PathVariable("id") int id){
        return studentService.getStudent(id);
    }

    @PostMapping("/students")
    public CustomResponse<Student> saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @PutMapping("/students")
    public CustomResponse<Student> updateStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") int id){
        studentService.deleteStudent(id);
    }
}
