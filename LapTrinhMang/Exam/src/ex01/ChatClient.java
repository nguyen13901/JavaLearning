package ex01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8000);
			System.out.println("Access server succesfully");
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			while(true) {
				System.out.println("Input string: ");
				String input = sc.nextLine();
				if ("exit".equals(input)) break;
				
				// send data to server
				dos.writeUTF(input);
				
				// get data from server
				System.out.println("Data from server");
				System.out.println("Upper case: " + dis.readUTF());
				System.out.println("Lower case: " + dis.readUTF());
				System.out.println("Length: " + dis.readUTF());
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
