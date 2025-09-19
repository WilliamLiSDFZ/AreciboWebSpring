package ai.arecibo.areciboweb.config;

import ai.arecibo.areciboweb.service.PasscodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminSecurityConfig implements WebMvcConfigurer {

    @Autowired
    private PasscodeService passcodeService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建拦截器实例并注入依赖
        AdminAccessInterceptor interceptor = new AdminAccessInterceptor();
        interceptor.setPasscodeService(passcodeService);
        
        registry.addInterceptor(interceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login", "/admin/static/**");
    }
}
