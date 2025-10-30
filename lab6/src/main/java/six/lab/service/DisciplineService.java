package six.lab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import six.lab.entity.Discipline;
import six.lab.response.CustomResponse;

@Service
public interface DisciplineService {

   CustomResponse<List<Discipline>> getAllDiscipline();

   CustomResponse<Discipline> saveDiscipline(Discipline discipline);

   CustomResponse<Discipline> getDiscipline(int id); 

   void deleteDiscipline(int id);
}