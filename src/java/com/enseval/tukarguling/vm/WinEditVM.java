/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enseval.tukarguling.vm;

import com.avaje.ebean.Ebean;
import com.enseval.tukarguling.model.Customer;
import com.enseval.tukarguling.model.Progress;
import com.enseval.tukarguling.model.User;
import com.enseval.tukarguling.util.AuthenticationService;
import com.enseval.tukarguling.util.AuthenticationServiceImpl;
import java.util.Date;
import java.util.List;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author Reza Elborneo
 */
public class WinEditVM {

    User user = new AuthenticationServiceImpl().getUserCredential().getUser();

    Progress p = new Progress();

    Customer customer;

    @Wire("#txt_cust_id")
    private Textbox txtCustID;
    @Wire("#txt_cust_name")
    private Textbox txtCustName;

    @Wire("#win_new_entry")
    private Window winNewEntry;
    
    boolean editable = true;

    @AfterCompose
    public void initSetup(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("selected_progress") Progress p,
            @ExecutionArgParam("editable") boolean e) {
        this.p = p;
        this.editable = e;
        Selectors.wireComponents(view, this, false);
    }

    @Command
    public void showCustomerList() {
        Executions.createComponents("win_customer_list.zul", null, null);
    }

    @GlobalCommand
    @NotifyChange({"p"})
    public void setCustomer(@BindingParam("customer") Customer customer) {
        this.p.setCustomer(customer);

    }

    @Command
    public void updateEntry() {

        Ebean.save(p);
        BindUtils.postGlobalCommand(null, null, "refresh", null);
        winNewEntry.detach();
        Messagebox.show("Update suksess!", "Info", Messagebox.OK, Messagebox.INFORMATION);

    }

    public Progress getP() {
        return p;
    }

    public void setP(Progress p) {
        this.p = p;
    }

    public Textbox getTxtCustID() {
        return txtCustID;
    }

    public void setTxtCustID(Textbox txtCustID) {
        this.txtCustID = txtCustID;
    }

    public Textbox getTxtCustName() {
        return txtCustName;
    }

    public void setTxtCustName(Textbox txtCustName) {
        this.txtCustName = txtCustName;
    }

    public Window getWinNewEntry() {
        return winNewEntry;
    }

    public void setWinNewEntry(Window winNewEntry) {
        this.winNewEntry = winNewEntry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

}
