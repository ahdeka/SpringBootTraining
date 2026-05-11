package com.back.domain.user.service;

import com.back.domain.user.dto.RegisterRequest;
import com.back.domain.user.dto.RegisterResponse;
import com.back.domain.user.entity.Role;
import com.back.domain.user.entity.User;
import com.back.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        log.info("회원가입 요청 email: {}", request.email());
        validateRegisterRequest(request);

        User user = createUser(request);

        log.info("회원가입 완료 userId: {}", user.getId());

        return new RegisterResponse(user.getEmail(), user.getNickname());
    }


    // --- private Helper Method ---
    private void validateRegisterRequest(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("이미 사용중인 이메일입니다.");
        }

        if (!request.password().equals(request.passwordConfirm())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        if (userRepository.existsByNickname(request.nickname())) {
            throw new RuntimeException("이미 사용중인 닉네임입니다.");
        }
    }

    private User createUser(RegisterRequest request) {
        User user = User.builder()
                .nickname(request.nickname())
                .password(request.password())
                .email(request.email())
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

}
