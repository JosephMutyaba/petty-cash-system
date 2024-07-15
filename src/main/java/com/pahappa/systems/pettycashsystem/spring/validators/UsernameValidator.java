package com.pahappa.systems.pettycashsystem.spring.validators;

import com.pahappa.systems.pettycashsystem.spring.dao.UserDAO;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@Service
public class UsernameValidator implements Validator<String> {

    @Autowired
    UserService userService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, String s) throws ValidatorException {
        if (userService.getByUsername(s)!=null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username already exists, try a different one"));
        }
    }
}