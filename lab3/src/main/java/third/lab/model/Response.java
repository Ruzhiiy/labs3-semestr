package third.lab.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Response {

    private String uid;
    private String operationUid;
    private String systemTime;
    private Codes code;
    private ErrorCodes errorCode;
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
