package io.swagger.interceptors;

import io.swagger.Repositories.AdminRepository;
import io.swagger.utils.JWTHandler;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 17.01.20.
 *
 * @author Max
 */
public class AdminCheckInterceptor extends HandlerInterceptorAdapter {

    private final AdminRepository adminRepository;

    public AdminCheckInterceptor(AdminRepository adminRepo) {
        Assert.notNull(adminRepo, "admin repo most not be null");
        this.adminRepository = adminRepo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("Authorization");
        String verifiedMail = JWTHandler.verifyJWT(token);

        if (verifiedMail.equals("")) { // token invalid
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        if ( !adminRepository.exists(verifiedMail)) { // not an admin
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }
        return true;
    }
}
