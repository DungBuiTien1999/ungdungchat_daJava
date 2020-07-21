/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPClient;

import UIClient.ScreenChatJFrame;
import UIClient.ScreenChatJInternalFrame;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUNG BUI
 */
public class ReceiveMessageFromOtherUserThread extends Thread{
    Socket ss ;
    String username;
//    javax.swing.JTextArea jTextAreaConversation;
    HashMap<String, ScreenChatJInternalFrame> chatwindows;
//    HashMap<String, ScreenChatJFrame> chatwindows;
    HashMap<String, javax.swing.JTextArea> showChatOnScreenHashMap;
    javax.swing.JDesktopPane jDesktopPane1;

    public ReceiveMessageFromOtherUserThread(Socket ss, String username, HashMap<String, ScreenChatJInternalFrame> chatwindows, HashMap<String, javax.swing.JTextArea> showChatOnScreenHashMap, javax.swing.JDesktopPane jDesktopPane1) {
        this.ss = ss;
        this.username = username;
//        this.jTextAreaConversation = jTextAreaConversation;
        this.chatwindows = chatwindows;
        this.showChatOnScreenHashMap = showChatOnScreenHashMap;
        this.jDesktopPane1 = jDesktopPane1;
    }

    @Override
    public void run() {
        InputStream is = null;
        try {
            is = ss.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String receivedMessage;
            do {                
                receivedMessage = br.readLine();
                if(receivedMessage == null){
                    continue;
                }
                
                Gson gson = new Gson();
                JsonObject jo = gson.fromJson(receivedMessage, JsonObject.class);
                Integer flag = Integer.parseInt(jo.get("flag").toString());
                
                if(flag == 2){
                    Message messFromOther = gson.fromJson(receivedMessage, Message.class);
                    String receiveperson = messFromOther.getUsersend();
                    
                    if(chatwindows.containsKey(receiveperson)){
                        chatwindows.get(receiveperson).setVisible(true);
                        System.out.println("co chua");
                    }else{
                        ScreenChatJInternalFrame scjif = new ScreenChatJInternalFrame(ss, username, receiveperson, chatwindows, showChatOnScreenHashMap, this.jDesktopPane1);
                        System.out.println("chua co nen phai tao");
                        this.jDesktopPane1.add(scjif);
                        scjif.setVisible(true);
                        chatwindows.put(receiveperson, scjif);
//                        ScreenChatJFrame scjf = new ScreenChatJFrame(ss, username, receiveperson, chatwindows, showChatOnScreenHashMap);
//                        scjf.setVisible(true);
//                        chatwindows.put(receiveperson, scjf);
                    }
                
                    String messString = "[" + messFromOther.getUsersend() + "]: " + messFromOther.content;
                    
                    javax.swing.JTextArea jTextAreaConversation = showChatOnScreenHashMap.get(receiveperson);
                
                    jTextAreaConversation.setText(jTextAreaConversation.getText().trim() + "\n" + messString);
                }
                
            } while (true);
            
        } catch (IOException ex) {
            Logger.getLogger(ReceiveMessageFromOtherUserThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
