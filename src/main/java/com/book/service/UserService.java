package com.book.service;

import com.book.domain.user.*;
import com.book.exception.DuplicateEmailException;
import com.book.exception.PasswordException;
import com.book.exception.UserNotFoundException;
import com.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public User signUp(UserCreateDto userCreateDto){
        checkDuplicateUser(userCreateDto.getEmail());
        userCreateDto.setPassword(encoder.encode(userCreateDto.getPassword()));
        User user = userCreateDto.toEntity();
        userRepository.save(user);
        return user;
    }

    public void checkDuplicateUser(String email){
        userRepository.findByEmail(email).ifPresent(param -> {
            throw new DuplicateEmailException("중복된 이메일입니다.");
        });
    }

    public User getUserInfo(String email){
        return userRepository.findByEmail(email).get();
    }

    public ProfileResDto getUserProfile(User user, Long id){
        User user2 = findUser(id);
        ProfileResDto profileResDto = user2.toProfile();
        if(user.getId().equals(id) || !user2.isHidden()){
            profileResDto.setMyBooks(user2.getMyBooks());
        }
        return profileResDto;
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void updatePassword(Long id, PasswordChangeDto password) {
        User user = findUser(id);
        String encPassword = encoder.encode(password.getCurPassword());
        if(user.getPassword().equals(encPassword)){
            user.updatePassword(password.getNewPassword());
        } else {
            throw new PasswordException("패스워드가 일치하지 않습니다.");
        }
    }

    @Transactional
    public void updateProfile(Long id, UserUpdateDto updateDto) {
        User user = findUser(id);
        user.updateProfile(updateDto);
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

}
