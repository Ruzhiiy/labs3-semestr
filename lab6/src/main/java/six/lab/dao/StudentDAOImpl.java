package six.lab.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import six.lab.entity.Student;

@Slf4j
@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {
        Query query = entityManager.createQuery("from Student");
        List<Student> allStudent = query.getResultList();
        log.info("get all students: {}", allStudent);
        return allStudent;
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        Query query = entityManager.createQuery("delete from Student where id = :studentId");
        query.setParameter("studentId", id);
        query.executeUpdate();
}
}
