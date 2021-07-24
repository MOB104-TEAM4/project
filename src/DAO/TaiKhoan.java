/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author admin
 */
public enum TaiKhoan {
    
    User1("admin","abc","Quản lý ứng dụng"),
    User2("user","abc","Nhân viên y tế");
    
    private String user ;
    private String pass ; 
    private String fullname ; 

    private TaiKhoan() {
    }

    private TaiKhoan(String user, String pass, String fullname) {
        this.user = user;
        this.pass = pass;
        this.fullname = fullname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    
    
    
}
