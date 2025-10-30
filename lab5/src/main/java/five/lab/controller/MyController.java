package five.lab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import five.lab.model.Request;
import five.lab.model.Response;
import five.lab.service.FeedbackProcessingService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MyController {

    private final FeedbackProcessingService feedbackProcessingService;

    @PostMapping(value="/feedback")
    public ResponseEntity<Response> feedback(
        @Valid @RequestBody Request request,
        BindingResult bindingResult
        ){ return feedbackProcessingService.processFeedback(request, bindingResult);}
}
