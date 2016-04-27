package com.enseval.tukarguling.model;

import com.avaje.ebean.Ebean;
import javax.persistence.*;
import javax.print.attribute.standard.*;
import java.awt.print.*;
import org.zkoss.zk.ui.*;
import net.sf.jasperreports.engine.type.*;
import net.sf.jasperreports.engine.export.*;
import java.util.logging.*;
import javax.print.attribute.*;
import javax.print.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.sql.Timestamp;
import net.sf.jasperreports.engine.*;

@Entity
public class Cetak {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    User userLogin;
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp wktCetak;
    String filePath;
    int cetakanKe;

//    @Transient
//    Setting setting = Ebean.find(Setting.class, 1);
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    public User getUserLogin() {
        return this.userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }

    public Timestamp getWktCetak() {
        return this.wktCetak;
    }

    public void setWktCetak(Timestamp wktCetak) {
        this.wktCetak = wktCetak;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getCetakanKe() {
        return this.cetakanKe;
    }

    public void setCetakanKe(int cetakanKe) {
        this.cetakanKe = cetakanKe;
    }

    public void doCetak(String printernya, String pdfPath) throws JRException, PrinterException, ArrayIndexOutOfBoundsException {
        
    }

}
