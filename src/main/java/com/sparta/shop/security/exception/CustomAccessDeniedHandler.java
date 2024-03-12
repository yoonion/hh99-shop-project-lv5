package com.sparta.shop.security.exception;

import com.sparta.shop.security.util.AuthResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;


@Slf4j(topic = "CustomAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("CustomAccessDeniedHandler 진입");
        AuthResponseUtil.authResultResponseBody(response, HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");

        /*response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("권한이 없습니다.");
        response.getWriter().flush();
        response.getWriter().close();*/
    }
}
