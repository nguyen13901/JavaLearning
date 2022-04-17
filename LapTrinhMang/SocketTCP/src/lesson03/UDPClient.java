package lesson03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
	
	private static final int PORT = 9001;
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket();
			InetAddress server = InetAddress.getByName("localhost");
			Scanner sc = new Scanner(System.in);
			while (true) {
				// Enter request
				System.out.println("Enter request: ");
				String request = sc.nextLine();
				if (request.equals("exit")) break;
				byte[] outputByte = request.getBytes();
				DatagramPacket outputPacket= new DatagramPacket(outputByte, outputByte.length, server, PORT);
				socket.send(outputPacket);
				
				// Get data from server
				byte[] inputByte = new byte[60000];
				DatagramPacket inputPacket = new DatagramPacket(inputByte, inputByte.length);
				socket.receive(inputPacket);
				
				// show data
				String inputStr = new String(inputPacket.getData(), 0, inputPacket.getLength());
				System.out.println("Data is get from server: " + inputStr);
			}
			sc.close();
			socket.close();
			System.out.println("Exit program");
		}catch(Exception e) {
			System.out.println("Error!!");
		}
	}
}
