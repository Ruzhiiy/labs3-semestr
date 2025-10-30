package third.lab.service;

import org.springframework.stereotype.Service;

import third.lab.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
