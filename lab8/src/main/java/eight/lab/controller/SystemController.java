package eight.lab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/.well-known")
public class SystemController {

    @GetMapping("/**")
    public ResponseEntity<String> handleWellKnown() {
        // Возвращаем пустой ответ для Chrome DevTools и других системных запросов
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
}
