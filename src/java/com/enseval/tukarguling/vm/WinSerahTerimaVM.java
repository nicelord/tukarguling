/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enseval.tukarguling.vm;

import com.enseval.tukarguling.model.Progress;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

/**
 *
 * @author user
 */


public class WinSerahTerimaVM {
    List<Progress> selectedProgress = new ArrayList<>();
    
    @AfterCompose
    public void initSetup(@ContextParam(ContextType.VIEW) Component view, 
            @ExecutionArgParam("selected_progress") List<Progress> selectedProgress) {
        
        this.selectedProgress = selectedProgress;
        Selectors.wireComponents(view, this, false);
    }

    public List<Progress> getSelectedProgress() {
        return selectedProgress;
    }

    public void setSelectedProgress(List<Progress> selectedProgress) {
        this.selectedProgress = selectedProgress;
    }
    
    
}
