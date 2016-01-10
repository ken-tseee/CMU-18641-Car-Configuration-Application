/**
 * @author Junjian Xie
 */
package driver;

import java.io.IOException;

import adapter.BuildAuto;
import exception.AutoException;

public class DriverUnit2PartA {
	public static void main(String[] args) throws IOException, AutoException {
		BuildAuto au = new BuildAuto();
		au.buildAuto("data/FordZTW.txt", "txt");
		au.printAuto("FordZTW");
		au.updateOptionSetName("FordZTW", "Power Moonroof", "Moonroof");
		au.updateOptionPrice("FordZTW", "Moonroof","not present", 99);
		System.out.println("After updating \"Power Moonroof\" and its price of \"not present\":\n");
		au.printAuto("FordZTW");
		System.out.println("Updating is over!");
	}
}