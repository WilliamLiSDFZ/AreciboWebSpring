package ai.arecibo.areciboweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**") // 匹配所有路径
                        .allowedOrigins("http://localhost:63343", "http://localhost:63344") // 允许你的前端 origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许所有方法
                        .allowedHeaders("*") // 允许所有请求头
                        .allowCredentials(true) // 如果前端有带 cookie
                        .maxAge(3600); // 预检请求缓存时间
            }
        };
    }
}
