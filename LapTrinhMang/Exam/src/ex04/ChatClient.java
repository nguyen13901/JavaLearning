package ex04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) {
		new ChatClient();
	}
	
	public ChatClient() {
		try {
			Scanner sc = new Scanner(System.in);
			Socket soc = new Socket("localhost", 8001);
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			System.out.println(dis.readUTF());
			String name = sc.nextLine();
			dos.writeUTF(name);
			new XulyNhan(soc).start();
			while (true) {
				String messenger = sc.nextLine();
				dos.writeUTF(messenger);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}

class XulyNhan extends Thread {
	Socket soc;	
	
	public XulyNhan(Socket soc ) {
		this.soc = soc;
	}
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			while (true) {
				System.out.println(dis.readUTF());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
