package lesson05;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class CaroServer {

	public static void main(String[] args) {
		new CaroServer();
	}

	static Vector<Xuly> clients = new Vector<Xuly>();
	static int n = 15;
	static List<Point> dadanh = new ArrayList<Point>();
	
	public CaroServer() {
		try {
			ServerSocket server = new ServerSocket(9001);
			Socket p1 = server.accept();
			// Player 1
			Xuly c1 = new Xuly(p1);
			clients.add(c1);
			c1.start();

			// Player 2
			Socket p2 = server.accept();
			Xuly c2 = new Xuly(p2);
			clients.add(c2);
			c1.start();
			
			//Viewer
			while (true) {
				Socket p3 = server.accept();
				Xuly c3 = new Xuly(p3);
				clients.add(c3);
			}

		} catch (Exception e) {

		}
	}
}

class Xuly extends Thread {
	Socket soc;
	DataOutputStream dos;
	DataInputStream dis;

	public Xuly(Socket soc) {
		try {
			this.soc = soc;
			dos = new DataOutputStream(soc.getOutputStream());
			dis = new DataInputStream(soc.getInputStream());
			for (Point p : CaroServer.dadanh) {
				dos.writeUTF(p.x+"");
				dos.writeUTF(p.y+"");
			}
		} catch (Exception e) {
		}
	}
	public void run() {
		try {
loop:		while(true) {
				int ix = Integer.parseInt(dis.readUTF());
				int iy = Integer.parseInt(dis.readUTF());
				///Can phai lam gi??!!!!!
				// kiem tra ca 2 client da vao hay chua?
				if (CaroServer.clients.size() < 2) continue;
				// Kiem tra co phai luot danh cua client nay hay khong!! 
				if (CaroServer.clients.get(0) == this && CaroServer.dadanh.size() % 2 != 0) continue;
				if (CaroServer.clients.get(1) == this && CaroServer.dadanh.size() % 2 != 1) continue;
				// Kiem tra toa do co phu hop khong
				if (ix < 0 || ix > CaroServer.n) continue;
				if (iy < 0 || iy > CaroServer.n) continue;
				for (Point p : CaroServer.dadanh) {
					if (ix == p.x && iy == p.y) 
						continue loop;
				}
				// Gui toa do cho tat ca client dang co!!
				for (Xuly c : CaroServer.clients) {
					try {
						c.dos.writeUTF(ix+"");
						c.dos.writeUTF(iy+"");
					}catch(Exception e) {
						
					}
				}
				
			}
		}catch(Exception e) {
			
		}
	}
}
