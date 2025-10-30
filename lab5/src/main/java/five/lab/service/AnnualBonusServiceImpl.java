package five.lab.service;

import java.time.Year;

import five.lab.model.Positions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions position, double salary, double bonus, int workDays){
        Year year = Year.now();
        int daysYear = year.length();
        log.info("Дней в году: {}", daysYear);
        return salary * bonus * daysYear * position.getPositionCoefficient()/ workDays;
    }
    
    @Override
    public double calculateQuarterlyBonus(Positions position, double salary, double bonus, int workDays){
        if (!position.isManager()) {
            log.warn("Квартальная премия доступна только для менеджеров. Позиция {} не является менеджерской", position);
            return 0.0;
        }
        
        int quarterlyDays = 90;
        log.info("Расчет квартальной премии для менеджера: {}", position);
        double result = salary * bonus * quarterlyDays * position.getPositionCoefficient() / (workDays/4);
        log.info("Результат расчета квартальной премии: {}", result);
        return result;
    }
}
