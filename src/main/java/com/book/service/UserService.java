package com.book.service;

import com.book.domain.user.*;
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
    public User register(UserCreateDto userCreateDto){
        User user = userCreateDto.toEntity();
        userRepository.save(user);
        return user;
    }

    public User getUserInfo(String email){
        return userRepository.findByEmail(email).get();
    }

    public ProfileResponse getUserProfile(User user, Long id){
        ProfileResponse profileResponse = null;
        if(user.getId().equals(id)){
            profileResponse = user.toProfileResponse(true);
        } else {
            User other = findUser(id);
            other.toProfileResponse(false);
        }
        return profileResponse;
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void updatePassword(Long id, PasswordRequest password) {
        User user = findUser(id);
        String encPassword = encoder.encode(password.getCurPassword());
        if(user.getPassword().equals(encPassword)){
            user.updatePassword(password.getNewPassword());
        } else {
            throw new PasswordException("패스워드가 일치하지 않습니다.");
        }
    }

    @Transactional
    public void updateName(Long id, UserUpdateDto updateDto) {
        User user = findUser(id);
        user.updateName(updateDto);
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

}
