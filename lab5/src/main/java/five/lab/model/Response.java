package five.lab.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Response {

    // Уникальный индетфикатор собщения
    private String uid;
    // Уникальный идентфикатор операции
    private String operationUid;
    // Время обращения запроса
    private String systemTime;
    // Годовая премия
    private Double annualBonus;
    // Квартальная премия
    private Double quartarlyBonus;
    // Код ответа сервера
    private Codes code;
    // Код ошибки 
    private ErrorCodes errorCode;
    // Cообщение ошибки
    private ErrorMessages errorMessage;


@Override
public String toString(){
    return String.format("Response{uid=%s, operationUid=%s, systemTime=%s, code=%s, errorCode=%s, errorMessage=%s}",
        uid, 
        operationUid,
        systemTime,
        code.getName(),
        errorCode.getName(),
        errorMessage.getName()
    );
}

}
