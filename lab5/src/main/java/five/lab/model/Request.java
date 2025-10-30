package five.lab.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    // Уникальный индетфикатор собщения
    @NotBlank(message = "uid не может быть пустым")
    @Size(max = 32, message = "uid обязательно, длина не более 32 символов")
    private String uid;
    
    // Уникальный идентфикатор операции
    @NotBlank(message = "operationUid не может быть пустым")
    @Size(max = 32, message = "operationUid обязательно, длина не более 32 символов")
    private String operationUid;

    // Тип системы 
    @NotNull(message = "Укажите тип системы: CRM, ERP, WMS")
    private Systems systemName;
    
    // Время обращения запроса
    private String systemTime;
    
    // Источник запроса
    private String source;
    
    // Должность соттрудника
    private Positions position;

    // Зарплата сотрудника
    private Double salary;

    // Премия сотрудника
    private Double bonus;

    // Количество отработанных дней в году
    private int workDays;
    
    // Уникальный идентфикатор коммуникации
    @NotNull
    @Min(value = 1, message = "comuncationId минимальное значение: 1")
    @Max(value = 100000, message = "communicationId не может быть более: 100 000")
    private int communicationId;

    // Идентификатор шаблона операции
    private int templateId;
    // Код продукта
    private int productCode;
    // Код sms сообщения
    private int smsCode;
    
    @Override
    public String toString(){
        return "{" +
               "uid=" + uid + " " +
               ", operationUid=" + operationUid + " " +
               ", systemName=" + systemName + " " +
               ", systemTime=" + systemTime + " " +
               ", source=" + source + " " +
               ", communicationId=" + communicationId + " " +
               ", templateId=" + templateId + " " +
               ", producCode=" + productCode + " " +
               ", smsCode=" + smsCode + " }";
               
    }

}
