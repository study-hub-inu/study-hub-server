package kr.co.studyhubinu.studyhubserver.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 블루그린 배포 시 4xx 에러 방지를 위함
 */

@RestController
public class defaultController {

    @GetMapping("/")
    @ApiIgnore
    public String home() {
        return "<h1>studyHub<h1>";
    }
}
