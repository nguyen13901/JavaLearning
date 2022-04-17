package ex01;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatUDPClient {
	private static final int PORT = 8001;
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket();
			System.out.println("Access server successfully");
			InetAddress server = InetAddress.getByName("localhost");
			while (true) {
				System.out.println("Input: ");
				String input = sc.nextLine();
				
				// send data 
				byte[] outputByte = input.getBytes();
				DatagramPacket outputPacket = new DatagramPacket(outputByte, outputByte.length, server, PORT);
				socket.send(outputPacket);
				
				// receive data
				byte[] upper = new byte[60000];
				DatagramPacket upperp = new DatagramPacket(upper, upper.length);
				socket.receive(upperp);
				
				String upperStr = new String(upperp.getData(), 0, upperp.getLength());
				System.out.println("Upper: " + upperStr);
				
				byte[] lower = new byte[60000];
				DatagramPacket lowerp = new DatagramPacket(lower, lower.length);
				socket.receive(lowerp);
				
				String lowerStr = new String(lowerp.getData(), 0, lowerp.getLength());
				System.out.println("Lower: " + lowerStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
