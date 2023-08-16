package kr.co.studyhubinu.studyhubserver.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.user.dto.request.SignUpRequest;
import kr.co.studyhubinu.studyhubserver.user.dto.request.SignInRequest;
import kr.co.studyhubinu.studyhubserver.user.dto.response.JwtLoginResponse;
import kr.co.studyhubinu.studyhubserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignUpRequest request) {
        userService.registerUser(request.toService());
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @Operation(summary = "로그인", description = "바디에 {email, password} 를 json 형식으로 보내주시면 됩니다. " +
            "email 은 꼭 email 형식으로 보내주셔야 합니다")
    @PostMapping("/login")
    public ResponseEntity<JwtLoginResponse> login(@RequestBody SignInRequest request) {

        return new ResponseEntity<>(HttpStatus.OK);
    }



//    @Operation(summary = "회원 정보 수정", description = "바디에 {name, email} 를 json 형식으로 보내주시고 jwt 토큰 bearer 헤더에" +
//            "보내주시면 됩니다")
//    @PutMapping("")
//    public ResponseEntity<CommonResponse> updateUser(@RequestBody UpdateUserRequest request, UserId userId) {
//
//        userService.updateUser(request, userId.getId());
//        CommonResponse response = new CommonResponse("회원 수정 완료 했습니다");
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @Operation(summary = "회원 탈퇴", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다")
//    @DeleteMapping
//    public ResponseEntity<CommonResponse> deleteUser(UserId userId) {
//
//        userService.deleteUser(userId.getId());
//        CommonResponse response = new CommonResponse("회원 탈퇴 완료 했습니다");
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


}
