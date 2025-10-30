package seven.lab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import seven.lab.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
