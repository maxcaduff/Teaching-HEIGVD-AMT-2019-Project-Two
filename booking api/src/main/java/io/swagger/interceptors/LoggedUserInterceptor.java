package io.swagger.interceptors;

import io.swagger.utils.JWTHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 17.01.20.
 *
 * @author Max
 */
public class LoggedUserInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("Authorization");
        String verifiedMail = JWTHandler.verifyJWT(token);

        if (verifiedMail.equals("")) { // token invalid
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        request.setAttribute("email", verifiedMail);
        return true;
    }
}
