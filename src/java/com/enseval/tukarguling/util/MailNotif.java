/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enseval.tukarguling.util;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author user
 */
public class MailNotif {

    public void emailGiroTolak() {

        String port = (Executions.getCurrent().getServerPort() == 80) ? "" : (":" + Executions.getCurrent().getServerPort());
        String url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath() + "/info_giro.zul";

    }

}
