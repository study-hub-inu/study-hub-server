package kr.co.studyhubinu.studyhubserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class defaultController {

    @GetMapping("/")
    @ApiIgnore
    public String home() {
        return "<h1>studyHub<h1>";
    }
}
