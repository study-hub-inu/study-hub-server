package kr.co.studyhubinu.studyhubserver.user.controller;

import kr.co.studyhubinu.studyhubserver.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

//    @MockBean
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @MockBean
//    private JwtAuthorizationFilter jwtAuthorizationFilter;




//    @Test
//    void 닉네임을_중복검사한다() throws Exception {
//        String url = "/api/users/duplication-nickname";
//        String nickName = "lee";
//
//        BDDMockito.given(userService.nicknameDuplicationValid(nickName));
//    }

}
