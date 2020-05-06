package com.npee.npeeblog.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. User"})
@RestController
@RequestMapping("/v1")
public class UserController {

    @ApiOperation(value = "회원 리스트 출력", notes = "회원 전체 리스트를 조회")
    @GetMapping("/users")
    public String readAllUsers() {
        return "User List";
    }
}
