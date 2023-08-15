package kr.co.studyhubinu.studyhubserver.email.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Email {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
}
