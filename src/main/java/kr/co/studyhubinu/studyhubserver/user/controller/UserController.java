package kr.co.studyhubinu.studyhubserver.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.dto.request.*;
import kr.co.studyhubinu.studyhubserver.user.dto.response.GetUserResponse;
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

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody SignUpRequest request) {
        userService.registerUser(request.toService());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "로그인", description = "바디에 {email, password} 를 json 형식으로 보내주시면 됩니다. " +
            "email 은 꼭 email 형식으로 보내주셔야 합니다")
    @PostMapping("/login")
    public ResponseEntity<JwtLoginResponse> login(@RequestBody SignInRequest request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 정보 수정", description = "바디에 {nickname, major} 를 json 형식으로 보내주시고 jwt 토큰 bearer 헤더에" +
            "보내주시면 됩니다")
    @PutMapping("")
    public ResponseEntity<HttpStatus> updateUser(@Valid @RequestBody UpdateUserRequest request, UserId userId) {
        userService.updateUser(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 단건 조회(상세정보)", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다 마이페이지에 사용됩니다")
    @GetMapping("")
    public ResponseEntity<GetUserResponse> getUser(UserId userId) {
        GetUserResponse response = userService.getUser(userId.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "닉네임 중복 검사")
    @GetMapping("/duplication-nickname")
    public ResponseEntity<HttpStatus> nicknameDuplicationValid(@RequestParam String nickname) {
        userService.nicknameDuplicationValid(nickname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "닉네임 수정", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다")
    @PutMapping("/nickname")
    public ResponseEntity<HttpStatus> updateNickname(@Valid @RequestBody UpdateNicknameRequest request, UserId userId) {
        userService.updateNickname(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "학과 수정", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다")
    @PutMapping("/major")
    public ResponseEntity<HttpStatus> updateMajor(@Valid @RequestBody UpdateMajorRequest request, UserId userId) {
        userService.updateMajor(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "비밀번호 수정", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다")
    @PutMapping("/password")
    public ResponseEntity<HttpStatus> updatePassword(@Valid @RequestBody UpdatePasswordRequest request, UserId userId) {
        userService.updatePassword(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 탈퇴", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다")
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteUser(UserId userId) {
        userService.deleteUser(userId.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
