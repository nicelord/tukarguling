package com.enseval.tukarguling.vm;

import com.avaje.ebean.*;
import org.zkoss.zk.ui.util.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zul.*;
import com.enseval.tukarguling.model.*;
import java.util.*;
import org.zkoss.bind.annotation.*;

public class MenuSystemSettingVM {

    List<User> users;
    List<User> selectedUsers;
    List<Printer> printers;
    User user;
    String filterID;
    String filterUsername;
    String filterAkses;
    String printernya;
    String akses;
    String namaPrinter, keterangan;
    boolean newUser;

    public MenuSystemSettingVM() {
        this.users = new ArrayList<User>();
        this.selectedUsers = new ArrayList<User>();
        this.printers = new ArrayList<Printer>();
        this.filterID = "";
        this.filterUsername = "";
        this.filterAkses = "";
        this.newUser = false;
    }

    @AfterCompose
    public void initSetup() {
        this.users = (List<User>) Ebean.find((Class) User.class).findList();
        this.printers = (List<Printer>) Ebean.find((Class) Printer.class).findList();
    }

    @Command
    @NotifyChange({"users"})
    public void saring() {
        this.users = (List<User>) Ebean.find((Class) User.class).where("id like '%" + this.filterID + "%' and username like '%" + this.filterUsername + "%' and akses like '%" + this.filterAkses + "%'").findList();
    }

    @Command
    @NotifyChange({"user", "newUser", "users", "printernya"})
    public void simpan() {
        if (this.newUser) {
            if (user.getNama().isEmpty() | user.getNama() == null
                    | user.getUsername().isEmpty() | user.getUsername() == null
                    | user.getPassword().isEmpty() | user.getPassword() == null
                    | user.getAkses().isEmpty() | user.getAkses() == null
                    | user.getDefPrinter().getNamaPrinter().isEmpty() | user.getDefPrinter().getNamaPrinter() == null) {
                Messagebox.show("Data tidak lengkap", "ERROR", Messagebox.OK, Messagebox.ERROR);
                return;
            }
            this.user.setNama(this.user.getNama());
            this.user.setUsername(this.user.getUsername());
            this.user.setPassword(this.user.getPassword());
            this.user.setDefPrinter(this.user.getDefPrinter());
            Ebean.save((Object) this.user);
            Clients.showNotification("User baru ditambahkan", "info", (Component) null, (String) null, 3000, true);
        } else {
            if (this.user == null) {
                Messagebox.show("User belum dipilih");
                return;
            }
            try {
                Ebean.save(this.user);
                
                Clients.showNotification("Perubahan disimpan", "info", (Component) null, (String) null, 3000, true);

            } catch (Exception ex) {
                
                Messagebox.show("Error", "ERROR", Messagebox.OK, Messagebox.ERROR);

            }
        }
        this.newUser = false;
        this.user = null;
        this.refresh();
    }

    @Command
    @NotifyChange({"user", "newUser", "printernya"})
    public void showDetail(@BindingParam("user") final User u) {
        this.newUser = false;
        this.user = u;
    }

    @Command
    @NotifyChange({"user", "newUser", "printernya"})
    public void tambahUser() {
        this.newUser = true;
        this.user = new User();
    }

    @Command
    @NotifyChange({"users", "selectedUsers"})
    public void hapusSelectedUsers() {
     
        Ebean.delete(this.selectedUsers);
        this.refresh();
    }

    @GlobalCommand
    @NotifyChange({"*"})
    public void refresh() {
        this.users = (List<User>) Ebean.find((Class) User.class).where("id like '%" + this.filterID + "%' and username like '%" + this.filterUsername + "%' and akses like '%" + this.filterAkses + "%'").findList();
        this.selectedUsers = new ArrayList<User>();
        this.newUser = false;
        this.user = null;
        this.printers = (List<Printer>) Ebean.find((Class) Printer.class).findList();
    }

     @Command
    @NotifyChange({"printers","namaPrinter","keterangan"})
    public void tambahPrinter() {
        if(this.namaPrinter.isEmpty() || this.keterangan.isEmpty()){
            return;
        }
        Printer p = new Printer();
        p.setNamaPrinter(namaPrinter);
        p.setKeterangan(keterangan);
        Ebean.save(p);
        refresh();
        this.namaPrinter = "";
        this.keterangan = "";
    }

    @Command
    @NotifyChange("printers")
    public void hapusPrinter(@BindingParam("printernya") Printer printernya) {
            

        Ebean.delete(printernya);
        refresh();

    }
    
    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getFilterID() {
        return this.filterID;
    }

    public void setFilterID(final String filterID) {
        this.filterID = filterID;
    }

    public String getFilterUsername() {
        return this.filterUsername;
    }

    public void setFilterUsername(final String filterUsername) {
        this.filterUsername = filterUsername;
    }

    public List<User> getSelectedUsers() {
        return this.selectedUsers;
    }

    public void setSelectedUsers(final List<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public List<Printer> getPrinters() {
        return this.printers;
    }

    public void setPrinters(final List<Printer> printers) {
        this.printers = printers;
    }

    public String getPrinternya() {
        return this.printernya;
    }

    public void setPrinternya(final String printernya) {
        this.printernya = printernya;
    }

    public String getAkses() {
        return this.akses;
    }

    public void setAkses(final String akses) {
        this.akses = akses;
    }

    public boolean isNewUser() {
        return this.newUser;
    }

    public void setNewUser(final boolean newUser) {
        this.newUser = newUser;
    }

    public String getFilterAkses() {
        return this.filterAkses;
    }

    public void setFilterAkses(final String filterAkses) {
        this.filterAkses = filterAkses;
    }

    public String getNamaPrinter() {
        return namaPrinter;
    }

    public void setNamaPrinter(String namaPrinter) {
        this.namaPrinter = namaPrinter;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

  
    
    
}
