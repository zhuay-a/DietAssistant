package com.example.dietAssistant.Interceptor;

import com.example.dietAssistant.JWT.JWTUtil;
import com.example.dietAssistant.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(jwtProperties.getUsertoken());

        try {
            Claims claims = JWTUtil.parseJWT(jwtProperties.getSecretkey(), token);
            //3、通过，放行
            response.setStatus(200);
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
}
