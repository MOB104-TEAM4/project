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
public class tmpImage {
    private String manv;
    private byte [] img;
    public tmpImage(){
        
    }
    
    
    public tmpImage(String manv, byte[] imgData) {
        this.manv=manv;
        this.img=img;
    }

    /**
     * @return the manv
     */
    public String getManv() {
        return manv;
    }

    /**
     * @param manv the manv to set
     */
    public void setManv(String manv) {
        this.manv = manv;
    }

    /**
     * @return the img
     */
    public byte [] getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(byte [] img) {
        this.img = img;
    }
    
    
}
