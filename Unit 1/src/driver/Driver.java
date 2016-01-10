/**
 * @author Junjian Xie
 */
package driver;

import model.Automobile;
import model.OptionSet;
import model.OptionSet.Option;
import util.FileIO;

public class Driver {
	public static void main(String [] args) {
		
		FileIO file = new FileIO();
		Automobile au = file.buildAutomotiveObject("data/FordZTW.txt");
		//Build Automobile Object from a file.
		au.print();
		//Print attributes before serialization
        System.out.println("Before serialization!\n");
		
        FileIO f = new FileIO(); 
        Automobile FordZTW = f.serializeAutomotive(au);
        //Serialize the object
        FordZTW.print();
        //Print new attributes.
        System.out.println("Serialization is over!\n");
        /**
         * Find method.
         */
        System.out.println("Now find automatic and Transmission!");
        Option opt = FordZTW.findOption("automatic ");
        OptionSet f_opset = FordZTW.findOptionSet("Transmission");
        FordZTW.print();
        System.out.println("Finding is over!\n");
        /**
         * Update method.
         */
        System.out.println("Now update standard, $0.0 of automatic and Side Impact Air Bags!");
        FordZTW.updateOption("standard ", "manual");
        FordZTW.updateOption("automatic ", 99);
		FordZTW.updateOptionSet("Side Impact Air Bags", "Air Bags");
		FordZTW.print();
		System.out.println("Updating is over!\n");
		 /**
         * Delete method.
         */
		System.out.println("Now delete ABS and Color!");
        FordZTW.deleteOption("ABS ");
		FordZTW.deleteOptionSet("Color");
		FordZTW.print();
		System.out.println("Deleting is over!\n"); 
	}
}
