/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPServer;

import TCPServer.Thread_Server.Thread_SocketServer;
import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author DUNG BUI
 */
public class Server_Socket {
    
    public static HashMap<String,Socket> userSocketHashMap;
    public static List<String> registedUserList;
    public static List<User> userList;
    
    public static void main(String[] args) throws IOException{
        File myFile = new File("registereduser.txt");
        if (myFile.createNewFile()){
            System.out.println("File is created!");
        }else{
            System.out.println("File already exists.");
        }
        
        BufferedReader br = new BufferedReader(new FileReader("registereduser.txt"));
                        String readLineString;
                        User user;
                        registedUserList = new ArrayList<>();
                        userList = new ArrayList<>();
                        userSocketHashMap = new HashMap<>();
                        Gson gson = new Gson();
                        while((readLineString = br.readLine())!=null){
                            user = gson.fromJson(readLineString, User.class);
                            registedUserList.add(user.getUsername());
                            userList.add(user);
                        }
        try
		{
			ServerSocket s = new ServerSocket(3200);
			
                        
			do
			{
				System.out.println("Waiting for a Client");
			
				Socket ss=s.accept(); //synchronous
				
                                new Thread_SocketServer(ss,registedUserList, userList, userSocketHashMap).start();
				
			}
			while (true);
		}
		catch(IOException e)
		{
			System.out.println("There're some error");
		}
    }
}
