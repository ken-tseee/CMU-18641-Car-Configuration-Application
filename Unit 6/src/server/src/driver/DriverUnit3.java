/**
 * @author Junjian Xie
 */
package driver;

import java.io.IOException;

import adapter.BuildAuto;
import adapter.ProxyAutomobile;
import exception.AutoException;
import scale.EditAuto;
import scale.EditOptions;

public class DriverUnit3 {
	public static void main(String[] args) throws AutoException, IOException {
		String fileName = "data/FordZTW.txt";
		String modelName = "FordZTW";
		
		ProxyAutomobile au = new BuildAuto(); 
		au.buildAuto(fileName, "txt");  
		EditAuto auto = new BuildAuto();  
		
		EditOptions r1 = new EditOptions(auto, modelName, "Side Impact Air Bags", "present");
		EditOptions r2 = new EditOptions(auto, modelName, "Side Impact Air Bags", "present");
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
	}
}
