package com.book.controller;

import com.book.domain.user.LoginDto;
import com.book.domain.user.UserCreateDto;
import com.book.service.LoginService;
import com.book.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(
            @Valid @RequestBody UserCreateDto userCreateDto) {
        userService.signUp(userCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, value = "error") String error,
                            @RequestParam(required = false, value = "exception") String exception,
                            Model model) {
        model.addAttribute(error);
        model.addAttribute(exception);
        return "login/login";
    }

//    @PostMapping("/login")
//    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
//        loginService.login(loginDto);
//        log.info("username = " + loginDto.getUsername());
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("/logout")
//    public ResponseEntity<Void> logout(){
//        loginService.logout();
//
//        return ResponseEntity.ok().build();
//    }
}
