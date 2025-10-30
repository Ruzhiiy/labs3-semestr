package five.lab.service;

import org.springframework.stereotype.Service;

import five.lab.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
