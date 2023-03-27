package com.book.service;

import com.book.domain.user.LoginDto;
import com.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final HttpSession session;
    private final BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public void loginValidation(LoginDto loginDto) throws Exception {
        String email = loginDto.getEmail();
        String encPassword = encoder.encode(loginDto.getPassword());
        if(!userRepository.existsByEmailAndPassword(email, encPassword)){
            throw new Exception("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }

    @Transactional(readOnly = true)
    public void login(LoginDto loginDto) throws Exception {
        loginValidation(loginDto);
        session.setAttribute("email", loginDto.getEmail());
    }

    public void logout() {
        session.removeAttribute("email");
    }
}
