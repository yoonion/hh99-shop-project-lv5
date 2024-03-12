package com.sparta.shop.security.exception;

import com.sparta.shop.security.util.AuthResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j(topic = "CustomAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("CustomAuthenticationEntryPoint 진입");
        AuthResponseUtil.authResultResponseBody(response, HttpServletResponse.SC_UNAUTHORIZED, "인증에 실패 했습니다. 로그인이 필요합니다.");

        /*response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("인증에 실패 했습니다.");
        response.getWriter().flush();
        response.getWriter().close();*/
    }
}
