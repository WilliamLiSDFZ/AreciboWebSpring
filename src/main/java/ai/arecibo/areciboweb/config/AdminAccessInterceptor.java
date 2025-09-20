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
        String passcode = request.getParameter("passcode");

        if (passcode != null && !passcode.trim().isEmpty()) {
            if (passcodeService.validatePasscode(passcode)) {
                passcodeService.updateLastLogin(passcode);
                HttpSession session = request.getSession();
                session.setAttribute("admin_authenticated", true);
                session.setAttribute("admin_passcode", passcode);
                return true;
            }
        }

        HttpSession session = request.getSession(false);
        if (session != null && Boolean.TRUE.equals(session.getAttribute("admin_authenticated"))) {
            return true;
        }

        response.sendRedirect("/admin/login");
        return false;
    }
}
