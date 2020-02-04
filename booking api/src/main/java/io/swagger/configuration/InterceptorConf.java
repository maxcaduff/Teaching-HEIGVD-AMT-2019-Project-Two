package io.swagger.configuration;

import io.swagger.Repositories.AdminRepository;
import io.swagger.interceptors.AdminCheckInterceptor;
import io.swagger.interceptors.LoggedUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created on 18.01.20.
 *
 * @author Max
 */
@Configuration
public class InterceptorConf extends WebMvcConfigurerAdapter {

    private final AdminRepository adminRepository;

    @Autowired
    public InterceptorConf(AdminRepository adminRepo) {
        Assert.notNull(adminRepo, "admin repo must not be null");
        this.adminRepository = adminRepo;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminCheckInterceptor(adminRepository))
                .addPathPatterns("/activity")
                .addPathPatterns("/location")
                .addPathPatterns("/activity/**")
                .addPathPatterns("/location/**")
                .excludePathPatterns("/activity/all")
                .excludePathPatterns("/activity/all/")
                .excludePathPatterns("/activity/availability/**")
                .excludePathPatterns("/location/all")
                .excludePathPatterns("/location/all/");
        registry.addInterceptor(new LoggedUserInterceptor())
                .addPathPatterns("/booking")
                .addPathPatterns("/booking/**");
    }
}