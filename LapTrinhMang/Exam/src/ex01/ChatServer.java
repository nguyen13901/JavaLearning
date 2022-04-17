package ex01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8000);
			System.out.println("Initial server succesfully");
			while(true) {
				Socket socket = server.accept();
				System.out.println("Socket " + socket.getLocalPort() + "access to server");
				
				// get data from client
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				String input =  dis.readUTF();
				
				// handle data
				String upperCase = input.toUpperCase();
				String lowerCase = input.toLowerCase();
				Integer length = input.length();
				
				// send data to client
				dos.writeUTF(upperCase);
				dos.writeUTF(lowerCase);
				dos.writeUTF(length.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
