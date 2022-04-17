package ex04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer1 {
	Vector<Xuly> clients = new Vector();

	public static void main(String[] args) {
		new ChatServer1();
	}

	public ChatServer1() {
		try {
			ServerSocket server = new ServerSocket(8001);
			System.out.println("success");
			while (true) {
				Socket soc = server.accept();
				Xuly x = new Xuly(soc, this);
				clients.add(x);
				x.start();
			}
		} catch (Exception e) {

		}
	}
}
class Xuly extends Thread {
	ChatServer1 cs;
	Socket soc;
	String name;
	public Xuly(Socket soc, ChatServer1 cs) {
		this.soc = soc;
		this.cs = cs;
	}
	public void run() {
		try {
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			dos.writeUTF("Please input your name");
			name = dis.readUTF();
			while (true) {
				// Nhanthongdiep
				String mgs = dis.readUTF();
				for (Xuly c : cs.clients) {
					try {
						if (c.soc == soc) continue;
						DataOutputStream dos1 = new DataOutputStream(c.soc.getOutputStream());
						dos1.writeUTF(name + "> " + mgs);
					} catch (Exception e) {

					}
				}

			}
		} catch (Exception e) {

		}
	}
}