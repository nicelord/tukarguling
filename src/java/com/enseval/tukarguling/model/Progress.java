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
    
    @ManyToOne
    Customer customer;
    @Transient
    String namaOutlet = "";
    
    
    
    String namaChecker;
    @Temporal(TemporalType.DATE)
    Date tglTerima;
    String remarkTerima="";
    
    String namaDirExp;
    String divisi;
    String namaUserSerah;
    @Temporal(TemporalType.DATE)
    Date tglSerah;
    String remarkSerah="";

    
    String namaPenerimaKembali;
    @Temporal(TemporalType.DATE)
    Date tglKembali;
    String remarkKembali="";
    


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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

 

    public String getNamaOutlet() {
        return namaOutlet;
    }

    public void setNamaOutlet(String namaOutlet) {
        this.namaOutlet = namaOutlet;
    }

    public String getNamaDirExp() {
        return namaDirExp;
    }

    public void setNamaDirExp(String namaDirExp) {
        this.namaDirExp = namaDirExp;
    }

    public String getNamaChecker() {
        return namaChecker;
    }

    public void setNamaChecker(String namaChecker) {
        this.namaChecker = namaChecker;
    }

    public String getNamaPenerimaKembali() {
        return namaPenerimaKembali;
    }

    public void setNamaPenerimaKembali(String namaPenerimaKembali) {
        this.namaPenerimaKembali = namaPenerimaKembali;
    }

    public Date getTglTerima() {
        return tglTerima;
    }

    public void setTglTerima(Date tglTerima) {
        this.tglTerima = tglTerima;
    }

    public Date getTglSerah() {
        return tglSerah;
    }

    public void setTglSerah(Date tglSerah) {
        this.tglSerah = tglSerah;
    }

    public Date getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(Date tglKembali) {
        this.tglKembali = tglKembali;
    }


    public String getRemarkTerima() {
        return remarkTerima;
    }

    public void setRemarkTerima(String remarkTerima) {
        this.remarkTerima = remarkTerima;
    }

    public String getRemarkSerah() {
        return remarkSerah;
    }

    public void setRemarkSerah(String remarkSerah) {
        this.remarkSerah = remarkSerah;
    }

    public String getRemarkKembali() {
        return remarkKembali;
    }

    public void setRemarkKembali(String remarkKembali) {
        this.remarkKembali = remarkKembali;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getNamaUserSerah() {
        return namaUserSerah;
    }

    public void setNamaUserSerah(String namaUserSerah) {
        this.namaUserSerah = namaUserSerah;
    }

 
    
    
    
}
