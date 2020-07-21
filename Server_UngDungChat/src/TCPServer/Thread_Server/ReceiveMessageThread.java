/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPServer.Thread_Server;

import TCPServer.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUNG BUI
 */
public class ReceiveMessageThread extends Thread{
    Socket ss;
    List<String> registedUserList;
    List<User> userList;
    HashMap<String, Socket> userSocketHashMap;
    static Lock lock = new ReentrantLock();

    public ReceiveMessageThread(Socket ss, List<String> list, List<User> userList, HashMap<String,Socket> userSocket) {
        this.ss = ss;
        this.registedUserList = list;
        this.userList = userList;
        this.userSocketHashMap = userSocket;
    }

    @Override
    public void run() {
        InputStream is=null;
        OutputStream os=null;
        try {
            is = ss.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            
            //Test khi khong dung thread
            os = ss.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            
            String receivedMessage;
            do {                
                receivedMessage = br.readLine();
                if(receivedMessage == null){
//                    System.out.println("dang cho");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(ReceiveMessageThread.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    continue;
                }
                //System.out.println("Received : " + receivedMessage);
                if (receivedMessage.equalsIgnoreCase("quit"))
                {
                    System.out.println("Client has left !");
                    break;
                }
                Gson gson = new Gson();
                JsonObject jo = gson.fromJson(receivedMessage, JsonObject.class);
                Integer flag = Integer.parseInt(jo.get("flag").toString());
                
                //kiem tra goi tin thuoc loai nao
                switch (flag) {
                    case 1:
                        {
                            //khi flag = 1 thi goi tin do la sign up
                            String mess;
                            String usernameString = jo.get("username").toString();
                            if(registedUserList.contains(usernameString)){
                                mess = "singupfail";
                            }else{
                                String passwordString = jo.get("password").toString();
                                User user = new User(usernameString,passwordString);
                                
                                String userString = gson.toJson(user, User.class);
                                System.out.println(userString);
                                PrintWriter writer = new PrintWriter(new FileWriter("registereduser.txt", true));
                                writer.println(userString);
                                writer.close();
                                
                                mess = "singupsuccess";
                                
                                lock.lock();
                                try{
                                    this.registedUserList.add(usernameString);
                                    this.userSocketHashMap.put(usernameString, ss);//them 1 user vao  list dang online
                                }finally{
                                    lock.unlock();
                                }
                                
                                new SendOnlineUsersThread(ss,usernameString, userSocketHashMap).start();
                            }       bw.write(mess);
                            bw.newLine();
                            bw.flush();
                            break;
                        }
                    case 0:
                        {
                            //khi flag = 0 thi goi tin do la login
                            String messString = "loginfail";
                            String usernameString = jo.get("username").toString();
                            String passwordString = jo.get("password").toString();
                            for(User item : userList){                    
                                if(item.getUsername().equalsIgnoreCase(usernameString)){
                                    if(item.getPassword().equalsIgnoreCase(passwordString)){
                                        messString = "loginsuccess";
                                        lock.lock();
                                        try{
                                            this.userSocketHashMap.put(usernameString, ss);//them 1 user vao  list dang online
                                        }finally{
                                            lock.unlock();
                                        }
                                        new SendOnlineUsersThread(ss, usernameString, userSocketHashMap).start();
                                        break;
                                    }
                                }
                            }
                            
                            bw.write(messString);
                            bw.newLine();
                            bw.flush();
                            break;
                        }
                    case 2://khi flag = 2 thi goi tin la message cua client nay gui cho client khac
                        String receiveperson = jo.get("userreceive").toString();
                        if(userSocketHashMap.containsKey(receiveperson)){
                            Socket receivepersonSocket = userSocketHashMap.get(receiveperson);
                            
                            OutputStream osRPS = receivepersonSocket.getOutputStream();
                            BufferedWriter bwRPS = new BufferedWriter(new OutputStreamWriter(osRPS));
                            
                            bwRPS.write(receivedMessage);
                            bwRPS.newLine();
                            bwRPS.flush();
                        }else 
                            System.out.println("khong tim thay client");
                        break;
                    default:
                        break;
                }
                
            } while (true);
            
            bw.close();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(ReceiveMessageThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(Thread_SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
}
