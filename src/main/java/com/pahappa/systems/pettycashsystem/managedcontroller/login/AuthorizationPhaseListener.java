package com.pahappa.systems.pettycashsystem.managedcontroller.login;

import org.springframework.stereotype.Component;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthorizationPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        LoginBean loginBean = (session != null) ? (LoginBean) session.getAttribute("loginBean") : null;

        String viewId = facesContext.getViewRoot().getViewId();

        if (!viewId.contains("login.xhtml") && (loginBean == null || loginBean.getLoggedInUser() == null)) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/pages/auth/login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // No action needed before phase
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
