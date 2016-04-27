package com.enseval.tukarguling.model;

import java.io.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class User implements Serializable
{
    @Id
    @GeneratedValue
    private Long id;
    String nama;
    String username;
    String password;
    String akses;

    @Transient
    Printer defPrinter;
    
    public User() {
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAkses() {
        return this.akses;
    }
    
    public void setAkses(String akses) {
        this.akses = akses;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNama() {
        return this.nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
  
    
    public Printer getDefPrinter() {
        return this.defPrinter;
    }
    
    public void setDefPrinter(Printer defPrinter) {
        this.defPrinter = defPrinter;
    }
}
