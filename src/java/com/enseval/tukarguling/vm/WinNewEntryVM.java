/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enseval.tukarguling.vm;

import com.avaje.ebean.Ebean;
import com.enseval.tukarguling.model.Progress;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;

/**
 *
 * @author Reza Elborneo
 */
public class WinNewEntryVM {

    Progress p = new Progress();

    @AfterCompose
    public void initSetup(@ContextParam(ContextType.VIEW) final Component view) {
        Selectors.wireComponents(view, this, false);
    }

    @Command
    public void showCustomerList() {
        Executions.createComponents("win_customer_list.zul", null, null);
    }

    
    public Progress getP() {
        return p;
    }

    public void setP(Progress p) {
        this.p = p;
    }

}
