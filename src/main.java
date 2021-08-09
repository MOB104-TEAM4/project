

import modelTmp.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class main {
    public static User userTmp = new User();
    public static final String user = "sa";
    public static final String pass = "123";
    public static ThongKe tk = new ThongKe();
    public static final String url = "jdbc:sqlserver://localhost:1433;database=project1;";
    public static void main(String[] args) {
        Loading ld = new Loading();
        ld.setVisible(true);
        
    }
}
