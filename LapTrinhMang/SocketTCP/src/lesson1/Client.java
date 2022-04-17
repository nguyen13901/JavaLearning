package lesson1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		System.out.println("Input your full name: ");
		String yourfName = getName();
		try {		
			Socket socket = new Socket("localhost",8001);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			dos.writeUTF(yourfName);
			String yourName = dis.readUTF();
			System.out.println("Your name: " + yourName);	
			
			socket.close();
		}catch(Exception e) {
			System.out.println("Error");
		}
	}
	
	private static String getName() {
		Scanner input = new Scanner(System.in);
		String output = input.nextLine();
		input.close();
		return output;
	}
}
