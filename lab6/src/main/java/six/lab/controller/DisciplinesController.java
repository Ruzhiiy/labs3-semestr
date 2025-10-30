package six.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import six.lab.entity.Discipline;
import six.lab.response.CustomResponse;
import six.lab.service.DisciplineService;

@RequestMapping("/api")
@RestController
public class DisciplinesController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/disciplines")
    public CustomResponse<List<Discipline>> allDisciplines(){
        return disciplineService.getAllDiscipline();
    }

    @GetMapping("/disciplines/{id}")
    public CustomResponse<Discipline> getDiscipline(@PathVariable("id") int id){
        return disciplineService.getDiscipline(id);
    }

    @PostMapping("/disciplines")
    public CustomResponse<Discipline> saveDiscipline(@RequestBody Discipline discipline){
        return disciplineService.saveDiscipline(discipline);
    }

    @PutMapping("/disciplines")
    public CustomResponse<Discipline> updateDiscipline(@RequestBody Discipline discipline){
        return disciplineService.saveDiscipline(discipline);
    }

    @DeleteMapping("/disciplines/{id}")
    public void deleteDiscipline(@PathVariable("id") int id){
        disciplineService.deleteDiscipline(id);
    }
}
