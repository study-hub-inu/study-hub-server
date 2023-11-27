package kr.co.studyhubinu.studyhubserver.support.repository;

import kr.co.studyhubinu.studyhubserver.config.JpaAuditingConfig;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest(properties = {"spring.jpa.properties.hibernate.jdbc.time_zone=Asia/Seoul"})
@Import({TestQueryDslConfig.class, JpaAuditingConfig.class})
public @interface RepositoryTest {
}
