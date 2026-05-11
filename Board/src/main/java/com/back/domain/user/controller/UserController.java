package com.back.domain.user.controller;

import com.back.domain.user.dto.RegisterRequest;
import com.back.domain.user.dto.RegisterResponse;
import com.back.domain.user.service.UserService;
import com.back.global.rsData.RsData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "유저", description = "유저 API 컨트롤러")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RsData<Void>> register(
            @Valid @RequestBody RegisterRequest request
            ) {

        userService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RsData.of("201", "회원가입이 완료됐습니다."));
    }
}
