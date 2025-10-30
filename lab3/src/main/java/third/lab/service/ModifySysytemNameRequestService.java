package third.lab.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import third.lab.model.Request;
import third.lab.model.Systems;

@Slf4j
@Service
public class ModifySysytemNameRequestService implements ModifyRequestService{

    @Override
    public void modify(Request request){
        request.setSystemName(Systems.SERVICE_ONE);

        log.debug("modify request = {}", request);
        
        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>(){}
        );
    }
}
