/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enseval.tukarguling.vm;

import com.avaje.ebean.Ebean;
import com.enseval.tukarguling.model.Progress;
import com.enseval.tukarguling.util.AuthenticationService;
import com.enseval.tukarguling.util.AuthenticationServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 *
 * @author user
 */


public class WinDokKembaliVM {
    List<Progress> selectedProgress = new ArrayList<>();
    
    @Wire("#win_dok_kembali")
    Window winDokKembali;

    @AfterCompose
    public void initSetup(@ContextParam(ContextType.VIEW) Component view, 
            @ExecutionArgParam("selected_progress") List<Progress> selectedProgress) {
        
        this.selectedProgress = selectedProgress;
        Selectors.wireComponents(view, this, false);
    }
    
    @Command
    public void update(){
        
        for (Progress p : selectedProgress) {
            p.setTglKembali(new Date());
            p.setNamaPenerimaKembali(new AuthenticationServiceImpl().getUserCredential().getName());
            Ebean.save(p);
        }
        winDokKembali.detach();
        BindUtils.postGlobalCommand(null, null, "refresh", null);
    }

    public List<Progress> getSelectedProgress() {
        return selectedProgress;
    }

    public void setSelectedProgress(List<Progress> selectedProgress) {
        this.selectedProgress = selectedProgress;
    }

    public Window getWinDokKembali() {
        return winDokKembali;
    }

    public void setWinDokKembali(Window winDokKembali) {
        this.winDokKembali = winDokKembali;
    }

    
}
