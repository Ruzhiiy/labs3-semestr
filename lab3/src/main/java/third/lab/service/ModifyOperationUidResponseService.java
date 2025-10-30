package third.lab.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import third.lab.model.Response;

@Slf4j
@Service
@Qualifier("ModifyOperationUidResponseService")
public class ModifyOperationUidResponseService implements ModifyResponseService{

    @Override
    public Response modify(Response response){
        UUID uuid = UUID.randomUUID();
        log.info("Изменяется поле operationUid объекта response");
        response.setOperationUid(uuid.toString());
        
        return response;
    }
}
