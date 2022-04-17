package lesson1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8001);
			System.out.println("Intiate server succesfull");
			System.out.println("Waiting request!!");
			
			while(true) {
				Socket socket = serverSocket.accept();
				new Handle(socket).run();
			}
			
		} catch(Exception e) {
			System.out.println("Intiate server failed");
		}
		
	}
}

class Handle extends Thread{
	
	private Socket socket;
	
	public Handle(Socket socket) {
		this.socket = socket;
		System.out.println("Hello " + socket.getPort());
	}
	
	public void run(){
		try {
			DataInputStream dis  = new DataInputStream(socket.getInputStream());
			DataOutputStream dos  = new DataOutputStream(socket.getOutputStream());
			
			String clientFullName = dis.readUTF();
			String clientName  = getNameClient(clientFullName);
			
			dos.writeUTF(clientName);
		}
		catch(Exception e) {
			System.out.println("Handle error");
		}
	}
	
	private String getNameClient(String clientFullName) {
		clientFullName = clientFullName.trim();
		int index = clientFullName.lastIndexOf(" ");
		String clientName = clientFullName.substring(index + 1);
		
		return clientName;
	}
}
