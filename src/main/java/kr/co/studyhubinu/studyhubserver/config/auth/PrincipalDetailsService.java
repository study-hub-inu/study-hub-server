package kr.co.studyhubinu.studyhubserver.config.auth;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.exception.UserException;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static kr.co.studyhubinu.studyhubserver.user.exception.UserErrorCode.USER_NOT_FOUND_EXCEPTION;


@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService : 진입");
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserException(USER_NOT_FOUND_EXCEPTION));

        log.info(userEntity.getEmail() + " " + userEntity.getPassword());

        return new PrincipalDetails(userEntity);
    }
}