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
public class NguoiCachLyTmp {
    private String macl;
    private String ten;
    private String gt;
    private String ns;
    private String sdt;
    private String dc;
    private String ncl;
    private String dien;
    private String tx;
    
    public NguoiCachLyTmp(){
        
    }
    
    public NguoiCachLyTmp(String macl,String ten,String gt,String ns,String sdt,String dc,String ncl,String dien,String tx){
        this.macl=macl;
        this.ten=ten;
        this.gt=gt;
        this.ns=ns;
        this.sdt=sdt;
        this.dc=dc;
        this.ncl=ncl;
        this.dien=dien;
        this.tx=tx;
    }

    /**
     * @return the macl
     */
    public String getMacl() {
        return macl;
    }

    /**
     * @param macl the macl to set
     */
    public void setMacl(String macl) {
        this.macl = macl;
    }

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * @return the gt
     */
    public String getGt() {
        return gt;
    }

    /**
     * @param gt the gt to set
     */
    public void setGt(String gt) {
        this.gt = gt;
    }

    /**
     * @return the ns
     */
    public String getNs() {
        return ns;
    }

    /**
     * @param ns the ns to set
     */
    public void setNs(String ns) {
        this.ns = ns;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the dc
     */
    public String getDc() {
        return dc;
    }

    /**
     * @param dc the dc to set
     */
    public void setDc(String dc) {
        this.dc = dc;
    }

    /**
     * @return the ncl
     */
    public String getNcl() {
        return ncl;
    }

    /**
     * @param ncl the ncl to set
     */
    public void setNcl(String ncl) {
        this.ncl = ncl;
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
     * @return the tx
     */
    public String getTx() {
        return tx;
    }

    /**
     * @param tx the tx to set
     */
    public void setTx(String tx) {
        this.tx = tx;
    }
    
}
