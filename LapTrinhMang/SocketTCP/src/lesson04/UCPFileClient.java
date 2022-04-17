package lesson04;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UCPFileClient {
	
	private static final int PORT = 9000;
	
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket();
			InetAddress serverAddress = InetAddress.getByName("localhost");
			Scanner sc = new Scanner(System.in);
			while(true) {
				// Send data to server - data has format : READ nameFile 
				System.out.println("Enter file name: ");
				String fileName = sc.nextLine();
				if (fileName.equals("exit")) break;
				
				String request = "READ " + fileName;
				byte[] outputBytes = request.getBytes();
				DatagramPacket outputPacket = new DatagramPacket(outputBytes, outputBytes.length, serverAddress, PORT);
				socket.send(outputPacket);
				
				// get data from server
				byte[] inputBytes = new byte[60000];
				DatagramPacket inputPacket = new DatagramPacket(inputBytes, inputBytes.length);
				socket.receive(inputPacket);
				//String inputStr = new String(inputPacket.getData(), 0, inputPacket.getLength());
				//System.out.println("Data get from server : \n" + inputStr);
				
				// write data into file -- file name default : Result 
				File file = new File("Result");
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(inputBytes);
				fos.close();
				System.out.println("Save data into file success!!");
			}
			sc.close();
			socket.close();
		}catch(Exception e) {
			System.out.println("Error");
		}
	}
}
