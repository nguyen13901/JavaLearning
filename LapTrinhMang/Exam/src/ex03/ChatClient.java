package ex03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		new ChatClient();
	}
	
	public ChatClient() {
		try {
			Socket socket = new Socket("localhost", 8000);
			System.out.println("Access server successfully");
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			System.out.print(dis.readUTF());
			String name = sc.nextLine();
			dos.writeUTF(name);
			new HandleClient(socket).start();
			while (true) {
				dos.writeUTF(sc.nextLine());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class HandleClient extends Thread {
	private Socket socket;
	
	public HandleClient(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				System.out.println(dis.readUTF());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}