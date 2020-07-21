/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPClient;

/**
 *
 * @author DUNG BUI
 */
public class User {
    Integer flag;
    String username;
    String password;

    public User(String username, String password, Integer flag) {
        this.username = username;
        this.password = password;
        this.flag = flag;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + '}';
    }
    
    
}
