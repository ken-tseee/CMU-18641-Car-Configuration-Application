/**
 * @author Ken
 */
package driver;

import java.util.LinkedHashMap;

import adapter.BuildAuto;

public class DriverUnit2 {
	public static void main(String[] args) {
		String fileName = "data/FordZTW.txt";
		String modelName = "FordZTW";
		
		BuildAuto au = new BuildAuto();
		au.BuildAuto(fileName);
		au.printAuto(modelName);
		
		System.out.println("Updating is coming next:\n");
	//	au.updateOptionSetName("FordZTW", "Brakes/Traction Control", "Brakes Control");
		au.updateOptionPrice("FordZTW", "Power Moonroof", "present", 999);
		au.printAuto(modelName);
		System.out.println("Updating is coming over!\n");
		
		System.out.println("Choice setting is coming next:\n");
		au.setOptionhoice("Color", "Cloud 9 White Clearcoat");
		au.setOptionhoice("Transmission", "automatic");
		au.setOptionhoice("Brakes/Traction Control", "Standard");
		au.setOptionhoice("Side Impact Air Bags", "not present");
		au.setOptionhoice("Power Moonroof", "present");
		au.printChoice();
		System.out.println("Choice setting is coming over!\n");
		
		float price = au.getTotalPrice();
		System.out.println("The total price is " + price + ".");
		/*
		System.out.println("The price for Cloud 9 White Clearcoat is $"+ au.getOptionChoicePrice("Cloud 9 White Clearcoat")+ ".");
		System.out.println("The price for automatic transmission is $"+ au.getOptionChoicePrice("automatic")+ ".");
		System.out.println("The price for standard brakes control is $"+ au.getOptionChoicePrice("Standard")+ ".");
		System.out.println("The price for non-present Side Impact Air Bags is $"+ au.getOptionChoicePrice("Side Impact Air Bags")+ ".");
		System.out.println("The price for present Power Moonroof is $"+ au.getOptionChoicePrice("present")+ ".");
		*/
		/*
		au.BuildAuto("data/FordZTW.txt");
		au.printAuto("FordZTW");
		au.updateOptionSetName("FordZTW", "Power Moonroof", "Moonroof");
		au.updateOptionPrice("FordZTW", "Moonroof","present ", 99);
		System.out.println("After updating \"Power Moonroof\":\n");
		au.printAuto("FordZTW");*/
	}
}
