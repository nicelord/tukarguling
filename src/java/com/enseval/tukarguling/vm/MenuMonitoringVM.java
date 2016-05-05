/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enseval.tukarguling.vm;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.enseval.tukarguling.model.Customer;
import com.enseval.tukarguling.model.Progress;
import com.enseval.tukarguling.model.User;
import com.enseval.tukarguling.util.AuthenticationServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author Reza Elborneo
 */
public class MenuMonitoringVM {

    List<Progress> listProgress = new ArrayList<>();
    List<Progress> selectedProgress = new ArrayList<>();
    User userLogin;

    Date filterTglBKB1, 
            filterTglBKB2,
            filterTglTerima1,
            filterTglTerima2,
            filterTglSerah1,
            filterTglSerah2,
            filterTglKembali1,
            filterTglKembali2;
    
    String filterNoTTRB = "",
            filterNoBKB = "",
            filterOutlet = "",
            filterChecker = "",
            filterSlsExp = "",
            filterPenerima = "";

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
    public void showEdit(@BindingParam("p") Progress p,
            @BindingParam("e") boolean e) {
        Map m = new HashMap();
        m.put("selected_progress", p);
        m.put("editable", e);
        Executions.createComponents("win_edit.zul", null, m);
    }

    @Command
    @NotifyChange("listProgress")
    public void hapus(@BindingParam("p") final Progress p) {

        Messagebox.show("Yakin akan dihapus?", "Konfirmasi", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onOK")) {
                    Ebean.delete(p);
                    BindUtils.postGlobalCommand(null, null, "refresh", null);
                }
            }
        });
    }

    @Command
    public void showSerahTerima() {
        if (selectedProgress.isEmpty()) {
            Messagebox.show("Tidak ada data yg dipilih!", "ERROR", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        Map m = new HashMap();
        m.put("selected_progress", selectedProgress);
        Executions.createComponents("win_serah_terima.zul", null, m);
    }

    @Command
    public void showDokKembali() {

        Iterator<Progress> ite = selectedProgress.iterator();

        while (ite.hasNext()) {
            Progress value = ite.next();
            if (value.getTglSerah() == null) {
                ite.remove();
            }
        }

        if (selectedProgress.isEmpty()) {
            Messagebox.show("Tidak ada data yg dipilih atau data tidak valid!", "ERROR", Messagebox.OK, Messagebox.ERROR);
            return;
        }

        Map m = new HashMap();
        m.put("selected_progress", selectedProgress);
        Executions.createComponents("win_dok_kembali.zul", null, m);
    }

    @Command
    public String loadNamaOutletById(Long id) {
        return Ebean.find(Customer.class, id) == null ? "no customer" : Ebean.find(Customer.class, id).getNama();
    }

    @Command
    @GlobalCommand
    @NotifyChange("listProgress")
    public void refresh(@BindingParam("customer") Customer customer) {
        this.listProgress = Ebean.find(Progress.class).where().orderBy("Id desc").findList();

    }

    @Command
    @NotifyChange({"listProgress"})
    public void updateFilter() {

        this.listProgress = Ebean.find(Progress.class)
                .where().like("noTTRB", "%" + this.filterNoTTRB + "%")
                .where().like("noBKB", "%" + this.filterNoBKB + "%")
                .or(Expr.like("customer.nama", "%" + this.filterOutlet + "%"), Expr.like("customer.id", "%" + this.filterOutlet + "%"))
                .where().like("namaChecker", "%" + this.filterChecker + "%")
                .or(Expr.like("namaDirExp", "%" + this.filterSlsExp + "%"), Expr.isNull("namaDirExp"))
                .or(Expr.like("namaPenerimaKembali", "%" + this.filterPenerima + "%"), Expr.isNull("namaPenerimaKembali"))
                .orderBy("Id DESC")
                .findList();

        if (this.filterTglBKB1 != null && this.filterTglBKB2 != null) {
            List<Progress> filteredProgress = Ebean.filter(Progress.class)
                    .between("tglBKB", this.filterTglBKB1, this.filterTglBKB2)
                    .filter(listProgress);
            this.listProgress = filteredProgress;

        }
        
        if (this.filterTglTerima1 != null && this.filterTglTerima2 != null) {
            List<Progress> filteredProgress = Ebean.filter(Progress.class)
                    .between("tglTerima", this.filterTglTerima1, this.filterTglTerima2)
                    .filter(listProgress);
            this.listProgress = filteredProgress;

        }
        
        if (this.filterTglSerah1 != null && this.filterTglSerah2 != null) {
            List<Progress> filteredProgress = Ebean.filter(Progress.class)
                    .between("tglSerah", this.filterTglSerah1, this.filterTglSerah2)
                    .filter(listProgress);
            this.listProgress = filteredProgress;

        }
        
        if (this.filterTglKembali1 != null && this.filterTglKembali2 != null) {
            List<Progress> filteredProgress = Ebean.filter(Progress.class)
                    .between("tglKembali", this.filterTglKembali1, this.filterTglKembali2)
                    .filter(listProgress);
            this.listProgress = filteredProgress;

        }
        
        
    }
    
    @Command
    @NotifyChange({"listProgress"})
    public void resetFilterTglBKB(){
        filterTglBKB1 = null;
        filterTglBKB2 = null;
        updateFilter();
    }
    
    @Command
    @NotifyChange({"listProgress"})
    public void resetFilterTglTerima(){
        filterTglTerima1 = null;
        filterTglTerima2 = null;
        updateFilter();
    }
    
    @Command
    @NotifyChange({"listProgress"})
    public void resetFilterTglSerah(){
        filterTglSerah1 = null;
        filterTglSerah2 = null;
        updateFilter();
    }
    
    @Command
    @NotifyChange({"listProgress"})
    public void resetFilterTglKembali(){
        filterTglKembali1 = null;
        filterTglKembali2 = null;
        updateFilter();
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

    public Date getFilterTglBKB1() {
        return filterTglBKB1;
    }

    public void setFilterTglBKB1(Date filterTglBKB1) {
        this.filterTglBKB1 = filterTglBKB1;
    }

    public Date getFilterTglBKB2() {
        return filterTglBKB2;
    }

    public void setFilterTglBKB2(Date filterTglBKB2) {
        this.filterTglBKB2 = filterTglBKB2;
    }

    public String getFilterNoTTRB() {
        return filterNoTTRB;
    }

    public void setFilterNoTTRB(String filterNoTTRB) {
        this.filterNoTTRB = filterNoTTRB;
    }

    public String getFilterNoBKB() {
        return filterNoBKB;
    }

    public void setFilterNoBKB(String filterNoBKB) {
        this.filterNoBKB = filterNoBKB;
    }

    public String getFilterOutlet() {
        return filterOutlet;
    }

    public void setFilterOutlet(String filterOutlet) {
        this.filterOutlet = filterOutlet;
    }

    public String getFilterChecker() {
        return filterChecker;
    }

    public void setFilterChecker(String filterChecker) {
        this.filterChecker = filterChecker;
    }

    public String getFilterSlsExp() {
        return filterSlsExp;
    }

    public void setFilterSlsExp(String filterSlsExp) {
        this.filterSlsExp = filterSlsExp;
    }

    public String getFilterPenerima() {
        return filterPenerima;
    }

    public void setFilterPenerima(String filterPenerima) {
        this.filterPenerima = filterPenerima;
    }

    public Date getFilterTglTerima1() {
        return filterTglTerima1;
    }

    public void setFilterTglTerima1(Date filterTglTerima1) {
        this.filterTglTerima1 = filterTglTerima1;
    }

    public Date getFilterTglTerima2() {
        return filterTglTerima2;
    }

    public void setFilterTglTerima2(Date filterTglTerima2) {
        this.filterTglTerima2 = filterTglTerima2;
    }

    public Date getFilterTglSerah1() {
        return filterTglSerah1;
    }

    public void setFilterTglSerah1(Date filterTglSerah1) {
        this.filterTglSerah1 = filterTglSerah1;
    }

    public Date getFilterTglSerah2() {
        return filterTglSerah2;
    }

    public void setFilterTglSerah2(Date filterTglSerah2) {
        this.filterTglSerah2 = filterTglSerah2;
    }

    public Date getFilterTglKembali1() {
        return filterTglKembali1;
    }

    public void setFilterTglKembali1(Date filterTglKembali1) {
        this.filterTglKembali1 = filterTglKembali1;
    }

    public Date getFilterTglKembali2() {
        return filterTglKembali2;
    }

    public void setFilterTglKembali2(Date filterTglKembali2) {
        this.filterTglKembali2 = filterTglKembali2;
    }

}
