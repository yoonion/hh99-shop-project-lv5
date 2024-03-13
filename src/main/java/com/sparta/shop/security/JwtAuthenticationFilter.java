package com.sparta.shop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.shop.dto.user.LoginRequestDto;
import com.sparta.shop.entity.user.UserRoleEnum;
import com.sparta.shop.jwt.JwtUtil;
import com.sparta.shop.security.util.AuthResponseUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/users/login");
    }

    // email, password를 통해 인증 토큰 생성 후 -> AuthenticationProviderManager 에게 위임
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter 진입");
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    // 로그인 성공시 호출 핸들러
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        log.info("JWT 로그인 성공");
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();

        String token = jwtUtil.createToken(username, role);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        AuthResponseUtil.authResultResponseBody(response, HttpServletResponse.SC_OK, "로그인 성공");
    }

    // 로그인 실패시 호출 핸들러
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.info("JWT 로그인 실패");
        AuthResponseUtil.authResultResponseBody(response, HttpServletResponse.SC_UNAUTHORIZED, "로그인 실패. 다시 로그인 해주세요.");
    }

}