package com.sparta.shop.service.user;

import com.sparta.shop.dto.user.SignUpRequestDto;
import com.sparta.shop.dto.user.SignUpResponseDto;
import com.sparta.shop.entity.user.User;
import com.sparta.shop.entity.user.UserGenderEnum;
import com.sparta.shop.entity.user.UserRoleEnum;
import com.sparta.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public SignUpResponseDto signup(SignUpRequestDto requestDto) {
        // 회원 권한 가져오기
        UserRoleEnum role = UserRoleEnum.getRoleByString(requestDto.getRole());

        // 회원 성별 가져오기
        UserGenderEnum gender = UserGenderEnum.getGenderByString(requestDto.getGender());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

         User user = new User(encodedPassword, gender, role, requestDto);
        userRepository.save(user);

        return new SignUpResponseDto(user);
    }
}
