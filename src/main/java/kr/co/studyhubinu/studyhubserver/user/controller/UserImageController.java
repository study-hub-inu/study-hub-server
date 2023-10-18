package kr.co.studyhubinu.studyhubserver.user.controller;

import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.service.UserImageService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/image")
public class UserImageController {

    private final UserImageService userImageService;

    @PostMapping("")
    public ResponseEntity<HttpStatus> uploadImage(UserId userId, MultipartFile image) throws IOException {
        userImageService.uploadUserImage(userId.getId() ,image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteImage(UserId userId) {
        userImageService.deleteUserImage(userId.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
