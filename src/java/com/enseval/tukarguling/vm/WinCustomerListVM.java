package com.enseval.tukarguling.vm;

import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zk.ui.*;
import com.avaje.ebean.*;
import org.zkoss.zk.ui.select.*;
import org.zkoss.bind.*;
import java.util.*;
import com.enseval.tukarguling.model.*;
import org.zkoss.zul.*;
import org.zkoss.bind.annotation.*;

public class WinCustomerListVM {

    Customer customer;
    List<Customer> listCustomers = new ArrayList<>();
    
    @Wire("#custWin")
    Window custWin;

    @Wire("#txtCari")
    Textbox txtCari;

    @AfterCompose
    public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }

    @Command
    @NotifyChange("listCustomers")
    public void cari() {
        this.listCustomers = Ebean.find(Customer.class).where().or(
                Expr.like("id", "%" + txtCari.getValue() + "%"),
                Expr.like("nama", "%" + txtCari.getValue() + "%"))
                .findList();
    }

    @Command
    public void pilihCustomer() {
        if(customer==null){
            Messagebox.show("Customer belum dipilih", "Error", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        Map m = new HashMap();
        m.put("customer",customer);
        BindUtils.postGlobalCommand(null, null, "setCustomer", m);
        custWin.detach();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getListCustomers() {
        return listCustomers;
    }

    public void setListCustomers(List<Customer> listCustomers) {
        this.listCustomers = listCustomers;
    }

    public Textbox getTxtCari() {
        return txtCari;
    }

    public void setTxtCari(Textbox txtCari) {
        this.txtCari = txtCari;
    }

    public Window getCustWin() {
        return custWin;
    }

    public void setCustWin(Window custWin) {
        this.custWin = custWin;
    }

}
