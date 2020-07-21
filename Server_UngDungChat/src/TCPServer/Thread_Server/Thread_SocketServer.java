/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPServer.Thread_Server;

import TCPServer.User;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author DUNG BUI
 */
public class Thread_SocketServer extends Thread{
    Socket ss;
    List<String> registedUserList;
    List<User> userList;
    HashMap<String, Socket> userSocketHashMap;
    
    public Thread_SocketServer(Socket s, List<String> list, List<User> userList, HashMap<String,Socket> userSocket){
        this.ss = s;
        this.registedUserList = list;
        this.userList = userList;
        this.userSocketHashMap = userSocket;
    }
    
    public void run(){
        ReceiveMessageThread rmt = new ReceiveMessageThread(ss, registedUserList, userList, userSocketHashMap);
        rmt.start();
    }
}
