package lesson02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9001);
			System.out.println("Intiate server success!!");
			System.out.println("Waiting service");
			while(true) {
				Socket socket = serverSocket.accept();
				new Handle(socket).run();
			}
		}catch(Exception e) {
			System.out.println("Intiate server failed!!");
		}
	}
}

class Handle extends Thread {
	private Socket socket;
	
	public Handle(Socket socket) {
		this.socket = socket;
		System.out.println("Hello socket " + socket.getPort());
	}
	
	public void run() {
		try {
			DataInputStream dis  = new DataInputStream(socket.getInputStream());
			DataOutputStream dos  = new DataOutputStream(socket.getOutputStream());
			
			String cal = dis.readUTF();
			char operator = cal.charAt(0);
			cal = cal.substring(cal.indexOf(" ") + 1);
			int numberOne = Integer.parseInt(cal.substring(0, cal.indexOf(" ") - 1));
			int numberTwo = Integer.parseInt(cal.substring(cal.indexOf(" ") + 1));
			int rs = calculation(numberOne, numberTwo, operator);
			dos.writeUTF(Integer.toString(rs));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
 	}
	
	public int calculation(int a, int b, char opr) throws Exception {
		if (opr == '*') return a * b;
		if (opr == '/') return a / b;
		if (opr == '+') return a + b;
		if (opr == '-') return a - b;
		throw new Exception("Error operator");
	}	
}