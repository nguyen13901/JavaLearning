package lesson02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		System.out.println("===============Your calculation: =============");
		try {
			Socket socket = new Socket("localhost",9001);
			System.out.println("Access server success");
			while(true) {
				System.out.println("Eneter your calculation: ");
				String cal = getCalculation();
				if (cal == "exit") break;
				
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				
				cal = cal.trim();
				char numberOne = cal.charAt(0);
				String numberTwo = cal.substring(cal.lastIndexOf(" ") + 1);
				String temp = cal.substring(cal.indexOf(" ") + 1);
				char operator = temp.charAt(0);
				temp = operator + " " + numberOne + " " + numberTwo;
				System.out.println(temp);
				dos.writeUTF(temp);
				System.out.println(">>>> Result: " + dis.readUTF());
			}
			socket.close();
		}catch(Exception e) {
			System.out.println("Access server failed");
		};
	}
	
	private static String getCalculation() {
		Scanner input = new Scanner(System.in);
	
		String cal = input.nextLine();
		input.close();

		return cal;
	}
	

}
