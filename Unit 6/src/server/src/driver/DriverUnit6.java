package driver;

import java.io.IOException;

import adapter.BuildAuto;
import database.Database;
import exception.AutoException;

public class DriverUnit6 {
	public static void main(String[] args) throws IOException, AutoException {
		Database db = new Database();
		db.initialize();
		
		String fileName1 = "data/FordZTW.txt";
		String fileName2 = "data/Prius.txt";
		String fileType = "txt";
		String modelName1 = "Ford ZTW";
		String modelName2 = "Toyota Prius";
		BuildAuto auto = new BuildAuto();
		auto.builAutomobile(fileName1, fileType);
		auto.printAuto(modelName1);
		auto.builAutomobile(fileName2, fileType);
		auto.printAuto(modelName2);
		
		System.out.println("Test update:");
		auto.updateOptionName(modelName2, "Side Impact Air Bags", "not present", "none");
		auto.updateOptionPrice(modelName2, "Power Moonroof", "not present", 88);
		System.out.println("Update result:");
		auto.printAuto(modelName2);

		System.out.println("Test delete:");
		auto.deleteAutomoblie(modelName1);
		System.out.println("Try printing Ford ZTW:");
		auto.printAuto(modelName1);
	}
}
