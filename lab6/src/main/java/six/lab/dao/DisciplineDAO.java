package six.lab.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import six.lab.entity.Discipline;

@Repository
public interface DisciplineDAO {

    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline discipline);

    Discipline getDiscipline(int id);

    void deleteDiscipline(int id);
}
