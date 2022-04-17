package ex02;

import java.io.IOException;
import java.net.ServerSocket;

public class OperationServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8000);
			System.out.println("Intiate server successfully");
			while(true) {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
