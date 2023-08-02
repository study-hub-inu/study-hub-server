package kr.co.studyhubinu.studyhubserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class defaultController {

    @GetMapping("/")
    public String home() {
        return "<h1>studyHub<h1>";
    }
}
