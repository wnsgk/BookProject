package com.book.service;

import com.book.domain.user.User;
import com.book.domain.user.UserCreateDto;
import com.book.domain.user.UserUpdateDto;
import com.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User register(UserCreateDto userCreateDto){
        User user = userCreateDto.toEntity();
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void updatePassword(Long id, String password) throws Exception {
        User user = findUser(id);
        user.updatePassword(password);
    }

    @Transactional
    public void updateUserName(Long id, UserUpdateDto updateDto) throws Exception {
        User user = findUser(id);
        user.updateName(updateDto);
    }

    public User findUser(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("존재하지 않는 사용자입니다."));
    }
}
