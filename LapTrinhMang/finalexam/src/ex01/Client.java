package ex01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8001);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			System.out.println("Input String: ");
			String input = sc.nextLine();
			dos.writeUTF(input);
			String output = dis.readUTF();
			System.out.println(output);
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}	
