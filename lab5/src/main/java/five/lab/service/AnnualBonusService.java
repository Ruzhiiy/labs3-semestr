package five.lab.service;

import five.lab.model.Positions;

public interface AnnualBonusService {

    double calculate(Positions positions, double salary, double bonus, int workDays);
    
    double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays);
}
