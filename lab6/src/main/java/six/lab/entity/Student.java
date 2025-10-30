package six.lab.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="STUDENTS")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "faculty", nullable = false)
    private String faculty;

    @Column(name = "age", nullable = false)
    private Integer age;
}
