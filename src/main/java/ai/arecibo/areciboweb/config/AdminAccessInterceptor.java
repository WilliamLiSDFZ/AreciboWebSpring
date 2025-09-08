package ai.arecibo.areciboweb.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminAccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 简单的管理员验证 - 在实际项目中应该使用更安全的方式
        String adminKey = request.getParameter("admin_key");
        
        // 这里使用简单的密钥验证，实际项目中应该使用更安全的认证方式
        if ("arecibo_admin_2025".equals(adminKey)) {
            HttpSession session = request.getSession();
            session.setAttribute("admin_authenticated", true);
            return true;
        }
        
        // 检查session中是否已认证
        HttpSession session = request.getSession(false);
        if (session != null && Boolean.TRUE.equals(session.getAttribute("admin_authenticated"))) {
            return true;
        }
        
        // 未认证，重定向到登录页面
        response.sendRedirect("/admin/login");
        return false;
    }
}
