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
import com.enseval.tukarguling.util.AuthenticationServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author Reza Elborneo
 */
public class MenuMonitoringVM {

    List<Progress> listProgress = new ArrayList<>();
    List<Progress> selectedProgress = new ArrayList<>();
    User userLogin;

    @AfterCompose
    public void initSetup() {
        this.userLogin = new AuthenticationServiceImpl().getUserCredential().getUser();
        this.listProgress = Ebean.find(Progress.class).where().orderBy("id desc").findList();
    }

    @Command
    public void showNewEntry() {
        Executions.createComponents("win_new_entry.zul", null, null);
    }

    @Command
    public void showSerahTerima() {

        Iterator<Progress> ite = selectedProgress.iterator();

        while (ite.hasNext()) {
            Progress value = ite.next();
            if (value.getTglSerah() != null) {
                ite.remove();
            }
        }
        Map m = new HashMap();
        m.put("selected_progress", selectedProgress);
        Executions.createComponents("win_serah_terima.zul", null, m);
    }

    @Command
    public String loadNamaOutletById(Long id) {
        return Ebean.find(Customer.class, id) == null ? "no customer" : Ebean.find(Customer.class, id).getNama();
    }

    @GlobalCommand
    @NotifyChange("listProgress")
    public void refresh(@BindingParam("customer") Customer customer) {
        this.listProgress = Ebean.find(Progress.class).where().orderBy("id desc").findList();

    }

    public List<Progress> getListProgress() {
        return listProgress;
    }

    public void setListProgress(List<Progress> listProgress) {
        this.listProgress = listProgress;
    }

    public User getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }

    public List<Progress> getSelectedProgress() {
        return selectedProgress;
    }

    public void setSelectedProgress(List<Progress> selectedProgress) {
        this.selectedProgress = selectedProgress;
    }

}
