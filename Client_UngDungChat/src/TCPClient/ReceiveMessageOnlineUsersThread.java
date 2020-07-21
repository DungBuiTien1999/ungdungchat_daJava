/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPClient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUNG BUI
 */
public class ReceiveMessageOnlineUsersThread extends Thread{
    Socket ss;
    List<String> onlineUserList;

    public ReceiveMessageOnlineUsersThread(Socket ss, List<String> onlineUsers) {
        this.ss = ss;
        this.onlineUserList = onlineUsers;
    }
    
    
    @Override
    public void run() {
        InputStream is = null;
        try {
            is = ss.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            String receivedMessage;
            
            do {                
                receivedMessage = br.readLine();
                if(receivedMessage == null){
                    continue;
                }
                Gson gson = new Gson();
                JsonObject jo = gson.fromJson(receivedMessage, JsonObject.class);
                Integer flag = Integer.parseInt(jo.get("flag").toString());
                
                if(flag == 0){//nhan danh sach nhung nguoi online
                    String listUserOnlineString = gson.fromJson(jo.get("onlineUserList").toString(), String.class);
                    listUserOnlineString = listUserOnlineString.replaceAll("\"", "");
                    List<String> tempList = new ArrayList<>(Arrays.asList(listUserOnlineString.split(",")));
                    this.onlineUserList.clear();
                    for (String item : tempList) {
                        this.onlineUserList.add(item);
                    }
                }
            } while (true);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ReceiveMessageOnlineUsersThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(ReceiveMessageOnlineUsersThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
