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

public class User {
    private String name;
    private String roler;
    private String username ;
    private byte [] dataImg;
    public User(){
        
    }
    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the roler
     */
    public String getRoler() {
        return roler;
    }

    /**
     * @param roler the roler to set
     */
    public void setRoler(String roler) {
        this.roler = roler;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the dataImg
     */
    public byte[] getDataImg() {
        return dataImg;
    }

    /**
     * @param dataImg the dataImg to set
     */
    public void setDataImg(byte[] dataImg) {
        this.dataImg = dataImg;
    }
    
    
}
