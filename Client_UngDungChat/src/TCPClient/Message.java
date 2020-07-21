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
public class Message {
    Integer flag;
    String usersend;
    String userreceive;
    String content;

    public Message(Integer flag, String usersend, String userreceive, String content) {
        this.flag = flag;
        this.usersend = usersend;
        this.userreceive = userreceive;
        this.content = content;
    }

    public String getUsersend() {
        return usersend;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getFlag() {
        return flag;
    }

    public String getUserreceive() {
        return userreceive;
    }

    public String getContent() {
        return content;
    }

    public void setUsersend(String usersend) {
        this.usersend = usersend;
    }

    public void setUserreceive(String userreceive) {
        this.userreceive = userreceive;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" + "usersend=" + usersend + ", userreceive=" + userreceive + ", content=" + content + '}';
    }
    
    
}
