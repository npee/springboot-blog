package com.npee.npeeblog.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Builder
@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Email
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false) // OAuth 로그인 시 불필요(로그인 방법 조건분기 결정 후 true로 변경예정)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String registerDate;
}