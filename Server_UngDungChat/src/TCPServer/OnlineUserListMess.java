/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPServer;

/**
 *
 * @author DUNG BUI
 */
public class OnlineUserListMess {
    Integer flag;
    String onlineUserList;

    public OnlineUserListMess(Integer flag, String OnlineUserList) {
        this.flag = flag;
        this.onlineUserList = OnlineUserList;
    }

    public Integer getFlag() {
        return flag;
    }

    public String getOnlineUserList() {
        return onlineUserList;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void setOnlineUserList(String OnlineUserList) {
        this.onlineUserList = OnlineUserList;
    }

    @Override
    public String toString() {
        return "OnlineUserListMess{" + "flag=" + flag + ", OnlineUserList=" + onlineUserList + '}';
    }
    
    
}
