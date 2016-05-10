/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enseval.tukarguling.vm;

import com.avaje.ebean.Ebean;
import com.enseval.tukarguling.model.Cetak;
import com.enseval.tukarguling.model.Printer;
import com.enseval.tukarguling.model.Progress;
import com.enseval.tukarguling.model.User;
import com.enseval.tukarguling.util.AuthenticationService;
import com.enseval.tukarguling.util.AuthenticationServiceImpl;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.type.OrientationEnum;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author user
 */
public class WinSerahTerimaVM {

    List<Progress> selectedProgress = new ArrayList<>();

    @Wire("#win_serah_terima")
    Window winSerahTerima;

    String txtDivisi, txtNamaPenerima;
    List<Progress> listNamaDirExp = new ArrayList(), listDivisi = new ArrayList<>();
    
    List<Printer> printers = Ebean.find(Printer.class).findList();
    
    User userLogin;
    
    @AfterCompose
    public void initSetup(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("selected_progress") List<Progress> selectedProgress) {

        this.selectedProgress = selectedProgress;
        listNamaDirExp = Ebean.find(Progress.class).select("namaDirExp").setDistinct(true).findList();
        listDivisi = Ebean.find(Progress.class).select("divisi").setDistinct(true).findList();
        userLogin = new AuthenticationServiceImpl().getUserCredential().getUser();
        Selectors.wireComponents(view, this, false);
    }

    @Command
    public void update() {
        for (Progress p : selectedProgress) {
            p.setDivisi(txtDivisi);
            p.setNamaDirExp(txtNamaPenerima);
            p.setTglSerah(new Date());
            p.setNamaUserSerah(new AuthenticationServiceImpl().getUserCredential().getName());
            Ebean.save(p);
        }

        cetak();
        winSerahTerima.detach();
        BindUtils.postGlobalCommand(null, null, "refresh", null);

    }

    public void cetak() {
        try {
            AttributeSet aset = new HashAttributeSet();
            aset.add(new PrinterName(userLogin.getDefPrinter().getNamaPrinter(), null));
            PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, aset);
            if (printService == null) {
                Messagebox.show("null printer", "Printer error", Messagebox.OK, Messagebox.ERROR);
                return;
            }
            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            Date date = new Date();
            String tgl = dateFormat.format(date);
            JRDataSource datasource = new JRBeanCollectionDataSource(selectedProgress);
            JRDataSource beanColDataSource = new JRBeanCollectionDataSource(selectedProgress);
            Map map = new HashMap();
            map.put("REPORT_DATA_SOURCE", datasource);
            map.put("DATA", beanColDataSource);
            map.put("TGL", tgl);
            map.put("PENERIMA", txtNamaPenerima);
            map.put("DIVISI", txtDivisi);
            map.put("USER", new AuthenticationServiceImpl().getUserCredential().getName());
            JasperPrint jasperPrint = JasperFillManager.fillReport(Executions.getCurrent().getDesktop().getWebApp().getRealPath("/") + "/report/serah-terima.jasper", map, (JRDataSource) new JREmptyDataSource());
            jasperPrint.setOrientation(OrientationEnum.PORTRAIT);
            //jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);
            if (!userLogin.getDefPrinter().getNamaPrinter().equals("noprint")) {
                JRExporter exporter = (JRExporter) new JRPrintServiceExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                System.out.println(printService[0].getAttributes());
                exporter.setParameter((JRExporterParameter) JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[0].getAttributes());
                exporter.setParameter((JRExporterParameter) JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                exporter.setParameter((JRExporterParameter) JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                exporter.exportReport();
            }
        } catch (JRException ex) {
            Logger.getLogger(WinSerahTerimaVM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Progress> getSelectedProgress() {
        return selectedProgress;
    }

    public void setSelectedProgress(List<Progress> selectedProgress) {
        this.selectedProgress = selectedProgress;
    }

    public Window getWinSerahTerima() {
        return winSerahTerima;
    }

    public void setWinSerahTerima(Window winSerahTerima) {
        this.winSerahTerima = winSerahTerima;
    }

    public String getTxtDivisi() {
        return txtDivisi;
    }

    public void setTxtDivisi(String txtDivisi) {
        this.txtDivisi = txtDivisi;
    }

    public String getTxtNamaPenerima() {
        return txtNamaPenerima;
    }

    public void setTxtNamaPenerima(String txtNamaPenerima) {
        this.txtNamaPenerima = txtNamaPenerima;
    }

    public List<Progress> getListNamaDirExp() {
        return listNamaDirExp;
    }

    public void setListNamaDirExp(List<Progress> listNamaDirExp) {
        this.listNamaDirExp = listNamaDirExp;
    }

    public List<Progress> getListDivisi() {
        return listDivisi;
    }

    public void setListDivisi(List<Progress> listDivisi) {
        this.listDivisi = listDivisi;
    }


    public List<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(List<Printer> printers) {
        this.printers = printers;
    }

    public User getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }

}
