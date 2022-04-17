package ex01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server =  new ServerSocket(8001);
			System.out.println("Initiate server succesfully");
			while (true) {
				Socket soc = server.accept();
				new Handle(soc).start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class Handle extends Thread {
	private Socket soc;
	
	private static final String PATTERNDATE = "dd/MM/yyyy";
	
	public Handle(Socket soc) {
		this.soc = soc;
		
	}
	
	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			String date = dis.readUTF();
			
			// handle data 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERNDATE);
			String output = "";
			if(isValid(date, formatter)) {
				LocalDate localDate = LocalDate.parse(date, formatter);

				output = formatter.format(localDate.plusDays(7));
			} else {
				output = formatter.format(LocalDate.now());
			}
			
			// send to client
			dos.writeUTF(output);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isValid(String dateStr, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
	
}