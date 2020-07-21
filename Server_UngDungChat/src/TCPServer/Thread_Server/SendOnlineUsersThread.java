/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPServer.Thread_Server;

import TCPServer.OnlineUserListMess;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUNG BUI
 */
public class SendOnlineUsersThread extends Thread{
    Socket ss;
    String username;
    HashMap<String, Socket> userSocketHashMap;
    static Lock lock = new ReentrantLock();

    public SendOnlineUsersThread(Socket ss,String UserName, HashMap<String, Socket> userSocket) {
        this.ss = ss;
        this.username = UserName;
        this.userSocketHashMap = userSocket;
    }

    @Override
    public void run() {
        OutputStream os=null;
        do {            
            try {
            if(ss.isClosed()){
                System.out.println("socket da dong");
                this.userSocketHashMap.remove(this.username);
                break;
            }
            os = ss.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                 
            Set<String> listUserOnline = this.userSocketHashMap.keySet();
            String listUserString = String .join(",", listUserOnline);
            
            OnlineUserListMess oulm = new OnlineUserListMess(0, listUserString);//gan flag = 0
            
            Gson gson = new Gson();
            String messString = gson.toJson(oulm, OnlineUserListMess.class);
                
            bw.write(messString);
            bw.newLine();
            bw.flush();
            
            try {
                    Thread.sleep(5000);
//            bw.close();
                } catch (InterruptedException ex) {
                    Logger.getLogger(SendOnlineUsersThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        } catch (IOException ex) {
            Logger.getLogger(SendOnlineUsersThread.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        finally {
//            try {
//                os.close();
//            } catch (IOException ex) {
//                Logger.getLogger(SendOnlineUsersThread.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        } while (true);
        try {
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(SendOnlineUsersThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
