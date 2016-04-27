package com.enseval.tukarguling.util;

import com.enseval.tukarguling.model.User;
import java.io.*;
import org.zkoss.zk.ui.*;
import com.avaje.ebean.*;

public class AuthenticationServiceImpl implements AuthenticationService, Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Override
    public UserCredential getUserCredential() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential)sess.getAttribute("userCredential");
        if (cre == null) {
            cre = new UserCredential();
            sess.setAttribute("userCredential", (Object)cre);
        }
        return cre;
    }
    
    @Override
    public boolean login(String username, String password) {
        User userlogin = (User)Ebean.find((Class)User.class).where("username = '" + username + "' and password = '" + password +"'").findUnique();
        if (userlogin == null) {
            return false;
        }
        Session sess = Sessions.getCurrent();
        UserCredential cre = new UserCredential(userlogin.getUsername(), userlogin.getNama(), userlogin);
        cre.setRoles(userlogin.getAkses());
        sess.setAttribute("userCredential", cre);
        return true;
    }
    
    @Override
    public void logout() {
        Session sess = Sessions.getCurrent();
        sess.removeAttribute("userCredential");
    }
}
