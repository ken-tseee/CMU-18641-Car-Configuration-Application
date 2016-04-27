package exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoException extends Exception {
	private String msg;
	private int ErrType;
	
	public AutoException() {
		
	}
	
	public AutoException(int ErrType, String msg) {
		this.ErrType= ErrType;
		this.msg = msg;
	}
	
	public String fix(int errNum) {
		Fix1to5 helper = new Fix1to5();
		return helper.fix(errNum);
	}
	
	public static void log(Object x) {
		System.out.println(x);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("ErrorInfo.txt", true));
			bw.write(new Date() + " -- " + x);
			bw.newLine();
			bw.close();
			// throw new IOException();
		} catch (IOException e) {
			e.printStackTrace();// System.out.println (e);
			System.out.println("failed to write " + x + " into file:" + "ErrorInfo.txt");// print out the rumtime error
			System.exit(0); // terminate the program or Runtime.getRuntime().exit(0); 
		}
	}
}
