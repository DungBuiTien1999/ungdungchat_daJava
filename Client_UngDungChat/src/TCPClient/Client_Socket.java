/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPClient;

import java.io.*;
import java.net.*;

/**
 *
 * @author DUNG BUI
 */
public class Client_Socket {
    public static void main(String[] args) {
        try
		{
			Socket s = new Socket("127.0.0.1",3200);
			System.out.println(s.getPort());
                        System.out.println(s.getInetAddress());
			
			InputStream is=s.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
     
			OutputStream os=s.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			
			String sentMessage="";
			String receivedMessage;
     
     		System.out.println("Talking to Server");
    		
			do
			{
				DataInputStream din=new DataInputStream(System.in);
				sentMessage=din.readLine();
				bw.write(sentMessage);
				bw.newLine();
				bw.flush();
				
				if (sentMessage.equalsIgnoreCase("quit"))
					break;
				else
				{
					receivedMessage=br.readLine();
					System.out.println("Received : " + receivedMessage);					
				}
				
			}
			while(true);
			    
			bw.close();
			br.close();
		}
		catch(IOException e)
		{
			System.out.println("There're some error");
		}
    }
}
