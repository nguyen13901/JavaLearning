package ex01;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatUDPServer {
	private static final int PORT = 8001;
	public static void main(String[] args) {
		try {
			DatagramSocket server = new DatagramSocket(PORT);
			System.out.println("Initate server sucessfully");
			while (true) {
				// receive data from client
				byte[] ibyte = new byte[60000];
				DatagramPacket input = new DatagramPacket(ibyte, ibyte.length);
				server.receive(input);
				
				
				// send data to client
				String inputStr = new String(input.getData(), 0, input.getLength());
				
				String upper = inputStr.toUpperCase();
				byte[] upperByte = upper.getBytes();
				DatagramPacket upperPacket = new DatagramPacket(upperByte, upperByte.length, input.getAddress(), input.getPort());
				server.send(upperPacket);
				
				String lower = inputStr.toLowerCase();
				byte[] lowerByte = lower.getBytes();
				DatagramPacket lowerPacket = new DatagramPacket(lowerByte, lowerByte.length, input.getAddress(), input.getPort());
				server.send(lowerPacket);
//				
//				Integer length = inputStr.length();
//				byte[] lengthByte = length.getBytes();
//				DatagramPacket lengthPacket = new DatagramPacket(lengthByte, lengthByte.length, input.getAddress(), PORT);
//				server.send(lengthPacket);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}	
