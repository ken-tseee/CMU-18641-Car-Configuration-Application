package driver;

import java.io.IOException;

import adapter.BuildAuto;

public class DriverUnit2 {
	public static void main(String[] args) throws IOException {
		String fileName = "data/FordZTW.txt";
		String modelName = "FordZTW";
		
		BuildAuto au = new BuildAuto();
		au.buildAuto(fileName, "txt");
		au.printAuto(modelName);
		
		System.out.println("Updating is coming next:\n");
	//	au.updateOptionSetName("FordZTW", "Brakes/Traction Control", "Brakes Control");
		au.updateOptionPrice("FordZTW", "Power Moonroof", "present", 999);
		au.printAuto(modelName);
		System.out.println("Updating is over!\n");
		
		System.out.println("Choice setting is coming next:\n");
		au.setOptionchoice("Color", "Cloud 9 White Clearcoat");
		au.setOptionchoice("Transmission", "standard");
		au.setOptionchoice("Brakes/Traction Control", "ABS");
		au.setOptionchoice("Side Impact Air Bags", "present");
		au.setOptionchoice("Power Moonroof", "present");
		au.printChoice();
		System.out.println("Choice setting is over!\n");
		
		System.out.println("The price for Cloud 9 White Clearcoat is $"+ au.getOptionChoicePrice("Color") + ".");
		System.out.println("The price for automatic transmission is $"+ au.getOptionChoicePrice("Transmission") + ".");
		System.out.println("The price for standard brakes control is $"+ au.getOptionChoicePrice("Brakes/Traction Control") + ".");
		System.out.println("The price for non-present Side Impact Air Bags is $"+ au.getOptionChoicePrice("Side Impact Air Bags") + ".");
		System.out.println("The price for present Power Moonroof is $"+ au.getOptionChoicePrice("Power Moonroof") + ".");
		float price = au.getTotalPrice();
		System.out.println("The total price is " + price + ".");
	}
}
