package kr.co.studyhubinu.studyhubserver.user.repository;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByNickname(String nickname);
}
