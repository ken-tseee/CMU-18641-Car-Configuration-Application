package model;

import java.io.Serializable;

import exception.AutoException;
import exception.ErrorType;
import model.OptionSet.Option;

public class Automobile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private float basePrice;
	private OptionSet opset[];
	/**
	 * Initialize an empty object of Automotive.
	 */
	public Automobile() {
	}
	/**
	 * Initialize an object of Automotive with some values.
	 */
	public Automobile(String name, float basePrice, int size) {
		this.name = name;
		this.basePrice = basePrice;
		this.opset = new OptionSet[size];
	}
	/**
	 * Initialize an object of OptionSet.
	 */
	public void setOptionSet(int n_opset, String name, int optionSetSize) {
		try {
			if(name.equals("") || name.equals(" ")) {
				throw new AutoException(ErrorType.NO_OPTIONSETNAME.ordinal(), "Exception: The OptionSet name is not found!");
			}
		} catch(AutoException ae) {
			name = ae.fix(ErrorType.NO_OPTIONSETNAME.ordinal());
		} finally {
		opset[n_opset] = new OptionSet(name, optionSetSize);
		}
	}
	/*
	public void setOptionSet(int n_opset, String name, int optionSetSize) {
		opset[n_opset] = new OptionSet(name, optionSetSize);
	}*/
	/**
	 * Initialize an object of Option.
	 */
	public void setOption(int n_opset, int n_opt, String name, float price) {
		opset[n_opset].setOption(n_opt, name, price);
	}
	
	public OptionSet getOptionSet(int n) {
		return opset[n];
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	/**
	 * Print data in particular format.
	 */
	public void print() {
		System.out.println("Model: " + name + "\nBase Price: $" + basePrice + "\n");
		for (int i = 0; i < opset.length; i++) {
			if (opset[i] != null)
				opset[i].print();
		}
		System.out.println("");
	}
	/**
	 * Find the OptionSet with names.
	 * @param name is a String that may be the same with an element.
	 * @return opset[i] is the one to be found.
	 */
	public OptionSet findOptionSet(String name) {
		for(int i=0; i<opset.length; i++) {
			if(opset[i].getName().equals(name)) {
				 System.out.println("The result is " + opset[i].getName() + ".");
				 return opset[i];
			}
		}
		System.out.println(name + " is not found!");
		return null;
	}
	/**
	 * Find the Option with names.
	 * @param name is a String that may be the same with an element.
	 * @return option is the one to be found.
	 */
	public Option findOption(String name) {
		for(int i=0; i<opset.length;i++) {
			Option option = opset[i].findOption(name);
			if(option != null) {
				return option;
			}
		}
		System.out.println(name + " is not found!");
		return null;
	}
	/**
	 * Update data in OptionSet with names.
	 * @param name1 is an element in the array.
	 * @param name2 is a new element for name1.
	 */
	public void updateOptionSet(String name1, String name2) {
		for(int i=0; i<opset.length; i++) {
			if(opset[i].getName().equals(name1)) {
				opset[i].setName(name2);
				return;
			}
		}
		System.out.println(name1 + " is not found!");
	}
	/**
	 * Update data in Option with names and the name of OptionSet.
	 * @param optionSetName
	 * @param optionName
	 * @param newName
	 */
	public void updateOption(String optionSetName, String optionName, String newName) {
		for(int i=0; i<opset.length; i++) {
			if(opset[i].getName().equals(optionSetName)) {
				opset[i].updateOption(optionName, newName);
				return;
			}
		}
		System.out.println(optionSetName + " is not found!");
	}
	/**
	 * Update data in Option with names.
	 * @param name1 is an element in the array.
	 * @param name2 is a new element for name1.
	 */
	public void updateOption(String name1, String name2) {
		for(int i=0; i<opset.length; i++) {
			Option option = opset[i].findOption(name1);
			if(option != null) {
				opset[i].updateOption(name1, name2);
				return;
			}
		}
		System.out.println(name1 + " is not found!");
	}
	/**
	 * Update the price in Option by finding its name and the name of OptionSet.
	 * @param name is an element in the array.
	 * @param newPrice is a new element for name.
	 */
	public void updateOption(String optionSetName, String optionName, float newPrice) {
		for(int i=0; i<opset.length; i++) {
			if(opset[i].getName().equals(optionSetName)) {
				opset[i].updateOption(optionName, newPrice);
				return;
			}
		}
		System.out.println(optionSetName + " is not found!");
	}
	/**
	 * Update the price in Option by finding its name.
	 * @param name is an element in the array.
	 * @param newPrice is a new element for name.
	 */
	public void updateOption(String name, float newPrice) {
		for(int i=0; i<opset.length; i++) {
			if(opset[i].findOption(name) != null) {
				opset[i].updateOption(name, newPrice);
				return;
			}
		}
		System.out.println(name + " is not found!");
	}
	/**
	 * Delete data in OptionSet.
	 * @param name is the name of the data to be deleted.
	 */
	public void deleteOptionSet(String name) {
		for(int i=0; i<opset.length; i++) {
			if(opset[i].getName().equals(name)) {
				opset[i] = null;
				return;
			}
		}
		System.out.println(name + " is not found!");
	}
	/**
	 * Delete data in Option.
	 * @param name is the name of the data to be deleted.
	 */
	public void deleteOption(String name) {
		for(int i=0; i<opset.length; i++) {
			if(opset[i].findOption(name) != null) {
				opset[i].deleteOption(name);
				return;
			}
		}
		System.out.println(name + " is not found!");
	}
}
