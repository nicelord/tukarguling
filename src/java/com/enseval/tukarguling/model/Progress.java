/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enseval.tukarguling.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author user
 */

@Entity
public class Progress implements Serializable {
    @Id
    @GeneratedValue        
    Long Id;
    String noTTRB = "";
    String noBKB = "";
    @Temporal(TemporalType.DATE)
    Date tglBKB = new Date(); 
    Long kodeOutlet;
    @Transient
    String namaOutlet = "";
    
    
    @ManyToOne
    User namaChecker;
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp waktuTerimaChecker;
    
    String namaDirExp;
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp waktuTerimaDirExp;

    @ManyToOne
    User namaPenerimakembali;
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp waktuKembali;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNoTTRB() {
        return noTTRB;
    }

    public void setNoTTRB(String noTTRB) {
        this.noTTRB = noTTRB;
    }

    public String getNoBKB() {
        return noBKB;
    }

    public void setNoBKB(String noBKB) {
        this.noBKB = noBKB;
    }

    public Date getTglBKB() {
        return tglBKB;
    }

    public void setTglBKB(Date tglBKB) {
        this.tglBKB = tglBKB;
    }

    public Long getKodeOutlet() {
        return kodeOutlet;
    }

    public void setKodeOutlet(Long kodeOutlet) {
        this.kodeOutlet = kodeOutlet;
    }

    public String getNamaOutlet() {
        return namaOutlet;
    }

    public void setNamaOutlet(String namaOutlet) {
        this.namaOutlet = namaOutlet;
    }

    public User getNamaChecker() {
        return namaChecker;
    }

    public void setNamaChecker(User namaChecker) {
        this.namaChecker = namaChecker;
    }

    public Timestamp getWaktuTerimaChecker() {
        return waktuTerimaChecker;
    }

    public void setWaktuTerimaChecker(Timestamp waktuTerimaChecker) {
        this.waktuTerimaChecker = waktuTerimaChecker;
    }

    public String getNamaDirExp() {
        return namaDirExp;
    }

    public void setNamaDirExp(String namaDirExp) {
        this.namaDirExp = namaDirExp;
    }

    public Timestamp getWaktuTerimaDirExp() {
        return waktuTerimaDirExp;
    }

    public void setWaktuTerimaDirExp(Timestamp waktuTerimaDirExp) {
        this.waktuTerimaDirExp = waktuTerimaDirExp;
    }

    public User getNamaPenerimakembali() {
        return namaPenerimakembali;
    }

    public void setNamaPenerimakembali(User namaPenerimakembali) {
        this.namaPenerimakembali = namaPenerimakembali;
    }

    public Timestamp getWaktuKembali() {
        return waktuKembali;
    }

    public void setWaktuKembali(Timestamp waktuKembali) {
        this.waktuKembali = waktuKembali;
    }
    
    
    
}
