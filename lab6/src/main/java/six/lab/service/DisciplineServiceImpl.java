package six.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import six.lab.dao.DisciplineDAO;
import six.lab.entity.Discipline;
import six.lab.response.CustomResponse;

@Slf4j
@Service
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    private DisciplineDAO disciplineDAO;

    @Override
    public CustomResponse<List<Discipline>> getAllDiscipline() {
        List<Discipline> disciplines = disciplineDAO.getAllDisciplines();
        return CustomResponse.success("Удача! Список всех дисциплин!", disciplines);
    }

    @Override
    public CustomResponse<Discipline> saveDiscipline(Discipline discipline) {
        try{
            disciplineDAO.saveDiscipline(discipline);
            return CustomResponse.success("Удача!!!", discipline);
        } catch (Exception e) {
            log.info(e.getMessage());
            return CustomResponse.fail("Неудача!!! Дисциплина не добавлена!", discipline);
        }
    }

    @Override
    public CustomResponse<Discipline> getDiscipline(int id) {
        Discipline discipline = disciplineDAO.getDiscipline(id);
        if(discipline != null) return CustomResponse.success("Удача! Вот информация о дисциплине!", discipline);
        else return CustomResponse.fail("Не удача! Такой дисциплины не нйдено!", discipline);
    }

    @Override
    public void deleteDiscipline(int id) {
        disciplineDAO.deleteDiscipline(id);
    }

}