package exception;

import java.io.FileWriter;
import java.io.IOException;

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
		log();
		return helper.fix(errNum);
	}
}
