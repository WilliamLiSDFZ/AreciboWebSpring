package ai.arecibo.areciboweb.config;

import ai.arecibo.areciboweb.service.PasscodeService;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminAccessInterceptor implements HandlerInterceptor {

    private PasscodeService passcodeService;

    public void setPasscodeService(PasscodeService passcodeService) {
        this.passcodeService = passcodeService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求参数或session中获取passcode
        String passcode = request.getParameter("passcode");
        
        // 如果请求中有passcode参数，验证它
        if (passcode != null && !passcode.trim().isEmpty()) {
            if (passcodeService.validatePasscode(passcode)) {
                // 验证成功，更新最后登录时间并设置session
                passcodeService.updateLastLogin(passcode);
                HttpSession session = request.getSession();
                session.setAttribute("admin_authenticated", true);
                session.setAttribute("admin_passcode", passcode);
                return true;
            }
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
