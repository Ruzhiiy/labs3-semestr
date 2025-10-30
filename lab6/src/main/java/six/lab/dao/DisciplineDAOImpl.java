package six.lab.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import six.lab.entity.Discipline;

@Repository
public class DisciplineDAOImpl implements DisciplineDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Discipline> getAllDisciplines() {
        Query query = entityManager.createQuery("from Discipline");
        List<Discipline> allDisciplines = query.getResultList();
        return allDisciplines;
    }

    @Override
    @Transactional
    public Discipline saveDiscipline(Discipline discipline) {
        return entityManager.merge(discipline);
    }

    @Override
    public Discipline getDiscipline(int id) {
        return entityManager.find(Discipline.class, id);
    }

    @Override
    @Transactional
    public void deleteDiscipline(int id) {
        Query query = entityManager.createQuery("delete from Discipline where id = :disciplineId");
        query.setParameter("disciplineId", id);
        query.executeUpdate();
    }

}
