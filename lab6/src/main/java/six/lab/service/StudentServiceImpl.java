package six.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import six.lab.dao.StudentDAO;
import six.lab.entity.Student;
import six.lab.response.CustomResponse;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;
    

    @Override
    public CustomResponse<List<Student>> getAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        return CustomResponse.success("Удача! Список всех студентов!", students);
    }

    @Override
    public CustomResponse<Student> saveStudent(Student student) {
        try{
            studentDAO.saveStudent(student);
            return CustomResponse.success("Удача!!!" ,student);
        } catch (Exception e) {
            return CustomResponse.fail("Не удача!!! Объект не сохранен!", student);
        }
    }

    @Override
    public CustomResponse<Student> getStudent(int id) {
        Student student = studentDAO.getStudent(id);
        if(student != null) return CustomResponse.success("Удача! Получена информация о студенте", student);
        else return CustomResponse.fail("Не удача! Такого студента не существует!", student);
    }

    @Override
    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }
}
