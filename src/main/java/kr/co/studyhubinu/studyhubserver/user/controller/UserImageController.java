package kr.co.studyhubinu.studyhubserver.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.service.UserImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserImageController {

    private final UserImageService userImageService;

    @Operation(summary = "회원 사진 저장", description = "jwt 토큰 bearer 헤더에" +
            "보내주시면 됩니다")
    @PostMapping("/v1/users/image")
    public ResponseEntity<HttpStatus> uploadImage(UserId userId, @RequestPart(name = "image", required = false) MultipartFile image) throws IOException {
        userImageService.uploadUserImage(userId.getId() ,image);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/v1/users/image")
    public ResponseEntity<HttpStatus> deleteImage(UserId userId) {
        userImageService.deleteUserImage(userId.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
