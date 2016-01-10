package model;

import java.util.ArrayList;

import util.FileIO;

public class driver {
	public static void main(String[] args) {
		FileIO file = new FileIO();
		Automobile au = file.buildAutomobileObject("data/FordZTW.txt");
		//Build Automobile Object from a file.
		au.print();
		
		au.setOptionChoice("Color", "Cloud 9 White Clearcoat");
		au.setOptionChoice("Transmission", "standard");
		au.setOptionChoice("Brakes/Traction Control", "ABS");
		au.setOptionChoice("Side Impact Air Bags", "present");
		au.setOptionChoice("Power Moonroof", "present");
		au.printChoice();
		System.out.println("Choice setting is over!\n");
		
		ArrayList<String> list = au.getOptionSetList();
		for(String optSet : list) {
			System.out.println(optSet);
			System.out.println(au.getOptionChoice(optSet) + " " + au.getOptionChoicePrice(optSet));
			
		}
	}
}
