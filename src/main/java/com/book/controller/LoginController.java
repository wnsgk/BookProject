package com.book.controller;

import com.book.domain.user.LoginDto;
import com.book.domain.user.UserCreateDto;
import com.book.service.LoginService;
import com.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(
            @Valid @RequestBody UserCreateDto userCreateDto) {
        userService.register(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginProc(@RequestBody LoginDto loginDto) {
        loginService.login(loginDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        loginService.logout();

        return ResponseEntity.ok().build();
    }
}
