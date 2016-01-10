/**
 * @author Ken
 */
package driver;

import adapter.BuildAuto;

public class DriverUnit2PartA {
	public static void main(String[] args) {
		BuildAuto au = new BuildAuto();
		au.BuildAuto("data/FordZTW.txt");
		au.printAuto("FordZTW");
		au.updateOptionSetName("FordZTW", "Power Moonroof", "Moonroof");
		au.updateOptionPrice("FordZTW", "Moonroof","present ", 99);
		System.out.println("After updating \"Power Moonroof\":\n");
		au.printAuto("FordZTW");
	}
}
