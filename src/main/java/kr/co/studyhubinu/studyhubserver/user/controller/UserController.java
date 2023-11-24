package kr.co.studyhubinu.studyhubserver.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtResponseDto;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.dto.request.*;
import kr.co.studyhubinu.studyhubserver.user.dto.response.GetUserResponse;
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
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/v1/users/signup")
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody SignUpRequest request) {
        userService.registerUser(request.toService());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "로그인", description = "바디에 {email, password} 를 json 형식으로 보내주시면 됩니다. " +
            "email 은 꼭 inu email 형식으로 보내주셔야 합니다")
    @PostMapping("/v1/users/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(userService.loginUser(signInRequest));
    }

    @Operation(summary = "회원 정보 수정", description = "바디에 {nickname, major} 를 json 형식으로 보내주시고 jwt 토큰 bearer 헤더에" +
            "보내주시면 됩니다")
    @PutMapping("/v1/users")
    public ResponseEntity<HttpStatus> updateUser(@Valid @RequestBody UpdateUserRequest request, UserId userId) {
        userService.updateUser(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 단건 조회(상세정보)", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다 마이페이지에 사용됩니다")
    @GetMapping("/v1/users")
    public ResponseEntity<GetUserResponse> getUser(UserId userId) {
        GetUserResponse response = userService.getUser(userId.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "닉네임 중복 검사")
    @GetMapping("/v1/users/duplication-nickname")
    public ResponseEntity<HttpStatus> nicknameDuplicationValid(@RequestParam String nickname) {
        userService.nicknameDuplicationValid(nickname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "닉네임 수정", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다")
    @PutMapping("/v1/users/nickname")
    public ResponseEntity<HttpStatus> updateNickname(@Valid @RequestBody UpdateNicknameRequest request, UserId userId) {
        userService.updateNickname(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "학과 수정", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다")
    @PutMapping("/v1/users/major")
    public ResponseEntity<HttpStatus> updateMajor(@Valid @RequestBody UpdateMajorRequest request, UserId userId) {
        userService.updateMajor(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "비밀번호 중복 검증", description = "jwt 토큰 bearer 헤더에 넣어주시고 parameter 칸에 비밀번호 넣어주세요")
    @PostMapping("/v1/users/password/verify")
    public ResponseEntity<HttpStatus> verifyPassword(@RequestBody VerifyPasswordRequest request, UserId userId) {
        userService.verifyPassword(userId.getId(), request.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "비밀번호 수정", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다")
    @PutMapping("/v1/users/password")
    public ResponseEntity<HttpStatus> updatePassword(@Valid @RequestBody UpdatePasswordRequest request, UserId userId) {
        userService.updatePassword(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 탈퇴", description = "jwt 토큰 bearer 헤더에 보내주시면 됩니다")
    @DeleteMapping("/v1/users")
    public ResponseEntity<HttpStatus> deleteUser(UserId userId) {
        userService.deleteUser(userId.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
