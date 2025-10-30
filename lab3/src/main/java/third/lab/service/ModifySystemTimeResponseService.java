package third.lab.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import third.lab.model.Response;
import third.lab.util.DateTimeUtil;

@Slf4j
@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService{

    @Override
    public Response modify(Response response){
        log.info("Изменяется поле systemTime объекта response");
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        return response;
    }

}
