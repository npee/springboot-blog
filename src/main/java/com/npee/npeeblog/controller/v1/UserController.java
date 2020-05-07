package com.npee.npeeblog.controller.v1;

import com.npee.npeeblog.advice.exception.CustomUserExistsException;
import com.npee.npeeblog.model.entity.Blog;
import com.npee.npeeblog.model.repository.BlogJpaRepository;
import com.npee.npeeblog.model.response.config.CommonResult;
import com.npee.npeeblog.model.entity.User;
import com.npee.npeeblog.model.repository.UserJpaRepository;
import com.npee.npeeblog.model.response.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Api(tags = {"1. User"})
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserJpaRepository userJpaRepository;
    private final BlogJpaRepository blogJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService;

    @ApiOperation(value = "회원 리스트 출력", notes = "회원 전체 리스트를 조회")
    @GetMapping("/users")
    public String readAllUsers() {
        return "User List";
    }

    @ApiOperation(value = "회원 추가", notes = "회원 정보 생성")
    @PostMapping("/user")
    public CommonResult createUser(
            @ApiParam(value = "회원 아이디", required = true) @RequestParam String email,
            @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String password) {

        Optional<User> user = userJpaRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new CustomUserExistsException();
        }

        List list = new ArrayList();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String now = format.format(date);

        // parsing
        String[] splitEmail = email.split("@");
        String nickname = splitEmail[0];

        User newUser = userJpaRepository.save(User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                //.nickname(email)
                .nickname(nickname)
                .registerDate(now)
                .build());

        Blog newBlog = blogJpaRepository.save(Blog.builder()
                .title(nickname + "의 블로그")
                .image("default url")
                .count(0L)
                .registerDate(now)
                .build());

        list.add(newUser);
        list.add(newBlog);

        // return responseService.getSingleResult(newUser);
        return responseService.getListResult(list);
    }
}
