package lesson04;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UCPFileServer {
	
	private static final int PORT = 9000;
	
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(PORT);
			System.out.println("Waiting service");
			while (true) {
				// get fileName from client
				byte[] inputBytes = new byte[60000];
				DatagramPacket inputPacket = new DatagramPacket(inputBytes, inputBytes.length);
				socket.receive(inputPacket);
				
				// get data from file that client request
				String inputStr = new String(inputPacket.getData(), 0, inputPacket.getLength());
				String fileName = inputStr.substring(5);
				File file = new File(fileName);
				String error = "";
				try {
					checkFile(fileName);
				}catch(Exception e) {
					error = e.getMessage();
				}
				byte[] outputBytes;
				if (error == "") {
					int fileLength = (int)file.length();
					FileInputStream fis = new FileInputStream(file);
					outputBytes = new byte[fileLength];
					fis.read(outputBytes);
					fis.close();
				} else { 
					outputBytes = error.getBytes();
				}
				// send data to client
				DatagramPacket outputPacket = new DatagramPacket(outputBytes, outputBytes.length, inputPacket.getAddress(), inputPacket.getPort());
				socket.send(outputPacket);
			}
		}catch(Exception e) {
			System.out.println("Error" + e.toString());
		}
	}
	
	public static void checkFile(String fileName) throws Exception {
		File file = new File(fileName);
		int maxLength = 1024 * 64;
		int fileLength = (int)file.length();
		if (!file.exists()) {
			throw new Exception("File does not exist");
		}
		if (fileLength > maxLength) {
			throw new Exception("File size exceeds the allowed size ");
		}
	}
}
