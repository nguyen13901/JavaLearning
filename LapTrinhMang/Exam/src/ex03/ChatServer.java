package ex03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	
	public List<Handle> listClient = new ArrayList<Handle>();
	
	public static void main(String[] args) {
		new ChatServer();
	}
	
	private ChatServer() {
		try {
			ServerSocket server = new ServerSocket(8000);
			while(true) {
				Socket socket = server.accept();
				Handle x = new Handle(this, socket);
				listClient.add(x);
				x.start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class Handle extends Thread {
	private ChatServer cs;
	private Socket socket;
	private String name;
	
	public Handle(ChatServer cs, Socket socket) {
		this.cs = cs;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Please input name: ");
			name = dis.readUTF();
			while (true) {
				String mgs = dis.readUTF();
				for (Handle c : cs.listClient) {
					try {
						if (c.socket == socket) break;
						DataOutputStream dos1 = new DataOutputStream(c.socket.getOutputStream());
						dos1.writeUTF(name + "> " + mgs);
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
