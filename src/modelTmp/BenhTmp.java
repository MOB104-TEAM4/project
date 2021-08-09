/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelTmp;

/**
 *
 * @author nghiacubu
 */
public class BenhTmp {
    private String mcl;
    private String ngay;
    private String dien;
    private String bh;
    private String gc;
    
    public BenhTmp(){
        
    }
    public BenhTmp(String mcl, String ngay, String dien, String bh, String gc) {
        this.mcl = mcl;
        this.ngay = ngay;
        this.dien = dien;
        this.bh = bh;
        this.gc = gc;
    }

    /**
     * @return the mcl
     */
    public String getMcl() {
        return mcl;
    }

    /**
     * @param mcl the mcl to set
     */
    public void setMcl(String mcl) {
        this.mcl = mcl;
    }

    /**
     * @return the ngay
     */
    public String getNgay() {
        return ngay;
    }

    /**
     * @param ngay the ngay to set
     */
    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    /**
     * @return the dien
     */
    public String getDien() {
        return dien;
    }

    /**
     * @param dien the dien to set
     */
    public void setDien(String dien) {
        this.dien = dien;
    }

    /**
     * @return the bh
     */
    public String getBh() {
        return bh;
    }

    /**
     * @param bh the bh to set
     */
    public void setBh(String bh) {
        this.bh = bh;
    }

    /**
     * @return the gc
     */
    public String getGc() {
        return gc;
    }

    /**
     * @param gc the gc to set
     */
    public void setGc(String gc) {
        this.gc = gc;
    }
    
    
}
