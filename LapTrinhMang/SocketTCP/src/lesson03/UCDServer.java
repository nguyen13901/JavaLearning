package lesson03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;

public class UCDServer {
	private static final int PORT = 9001;
	public static void main(String[] args) {
		try {
			DatagramSocket serverSocket = new DatagramSocket(PORT);
			System.out.println("Waiting service");
			while (true) {
				// receive data from client
				byte[] inputBytes = new byte[1];
				DatagramPacket inputPacket = new DatagramPacket(inputBytes, 0);
				serverSocket.receive(inputPacket);
				
				// send data to client
				LocalDateTime systemTime = LocalDateTime.now();
				String stStr = getSystemTimeStr(systemTime);
				byte[] outputBytes = stStr.getBytes();
				DatagramPacket outputPacket = new DatagramPacket(outputBytes, outputBytes.length, inputPacket.getAddress(), inputPacket.getPort());
				serverSocket.send(outputPacket);
			}
		}catch(Exception e) {
			System.out.println("Error!!");
		}
	}
	
	public static String getSystemTimeStr(LocalDateTime st) {
		int day = st.getDayOfMonth();
		int month = st.getMonthValue();
		int year = st.getYear();
		int hour = st.getHour();
		int minute = st.getMinute();
		int second = st.getSecond();
		return day + "/" + month + "/" + year + "--" + hour + ":" + minute + ":" + second;
	}
}
