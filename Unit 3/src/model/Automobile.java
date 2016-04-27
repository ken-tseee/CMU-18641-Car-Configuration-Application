package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;
import exception.ErrorType;
import model.OptionSet.Option;

public class Automobile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private float basePrice;
	
	private String make;
	private String model;
	private ArrayList<OptionSet> optSet;
	private ArrayList<Option> choice;
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * Initialize an empty object of Automotive.
	 */
	public Automobile() {
	}
	/**
	 * Initialize an object of Automotive with some values.
	 */
	public Automobile(String make, String model, float basePrice) {
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		this.optSet = new ArrayList<OptionSet>();
		this.choice = new ArrayList<Option>();
	}
	/**
	 * Initialize an object of OptionSet.
	 */
	public void setOptionSet(String optSetName) {
		try {
			if(optSetName.equals("") || optSetName.equals(" ")) {
				throw new AutoException(ErrorType.NO_OPTIONSETNAME.ordinal(), "Exception: The OptionSet name is not found!");
			}
		} catch(AutoException ae) {
			AutoException.log(ae);
			optSetName = ae.fix(ErrorType.NO_OPTIONSETNAME.ordinal());
		} finally {
		optSet.add(new OptionSet(optSetName));
		}
	}
	/**
	 * Initialize an object of Option.
	 */
	public void setOption(int n_optSet, String name, float price) {
		optSet.get(n_optSet).setOption(name, price);
	}
	
	public OptionSet getOptionSet(int n) {
		return optSet.get(n);
	}
	
	public String getOptionSetName(int n) {
		return optSet.get(n).getName();
	}
	
	public int getOptionSetSize() {
		return optSet.size();
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
		System.out.println("Make: " + make + "\nModel: " + model + "\nBase Price: $" + basePrice + "\n");
		for (int i=0; i<optSet.size(); i++) {
			if (optSet.get(i) != null)
				optSet.get(i).print();
		}
		System.out.println("");
	}
	
	public void printChoice() {
		for(int i=0; i<optSet.size(); i++) {
			optSet.get(i).printChoice();
		}
	}
	
	public void setOptionChoice(String setName, String optionName) {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getName().equals(setName)) {
				optSet.get(i).setChoice(optionName);
			}
		}
	}
	
	public String getChoice(String setName) {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getName().equals(setName)) {
				return optSet.get(i).getOptionChoiceName();
			}
		}
		return null;
	}
	
	public float getOptionChoicePrice(String setName) {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getChoice().getName().equals(setName)) {
				return optSet.get(i).getOptionChoicePrice(setName);
			}
		}
		return 0;
	}
	
	public float getTotalPrice() {
		setChoiceList();
		float sum = basePrice;
		for(int i=0; i<choice.size(); i++) {
			sum += choice.get(i).getPrice();
		}
		return sum;
	}
	/**
	 * Keep track of choices.
	 */
	public void setChoiceList() {
		for(int i=0; i<optSet.size(); i++) {
			choice.add(optSet.get(i).getChoice());
		}
	}
	
	/**
	 * Find the OptionSet with names.
	 * @param name is a String that may be the same with an element.
	 * @return opset[i] is the one to be found.
	 */
	public OptionSet findOptionSet(String name) {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getName().equals(name)) {
				 System.out.println("The result is " + optSet.get(i).getName() + ".");
				 return optSet.get(i);
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
		for(int i=0; i<optSet.size();i++) {
			Option option = optSet.get(i).findOption(name);
			if(option != null) {
				return option;
			}
		}
		System.out.println(name + " is not found!");
		return null;
	}
	
	public float findOptionPrice(String setName, String option) {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getName().equals(setName)) {
				return optSet.get(i).findOptionPrice(option);
			}
		}
		return 0;
	}
	
	public String findOptionName(String setName, String option) {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getName().equals(setName)) {
				return optSet.get(i).findOptionName(option);
			}
		}
		return null;
	}
	/**
	 * Update data in OptionSet with names.
	 * @param name1 is an element in the array.
	 * @param name2 is a new element for name1.
	 * @throws AutoException 
	 */
	public void updateOptionSet(String name1, String name2) throws AutoException {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getName().equals(name1)) {
				optSet.get(i).setName(name2);
				return;
			}
		}
		throw new AutoException(ErrorType.NO_OPTIONSETNAME.ordinal(), "Exception: The OptionSet name is not found");
	}
	/**
	 * Update data in Option with names and the name of OptionSet.
	 * @param optionSetName
	 * @param optionName
	 * @param newName
	 */
	public void updateOptionName(String optionSetName, String optionName, String newName) {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getName().equals(optionSetName)) {
				optSet.get(i).updateOptionName(optionName, newName);
				return;
			}
		}
		System.out.println(optionSetName + " is not found!");
	}
	/**
	 * Update the price in Option by finding its name and the name of OptionSet.
	 * @param name is an element in the array.
	 * @param newPrice is a new element for name.
	 */
	public void updateOptionPrice(String optionSetName, String optionName, float newPrice) throws AutoException {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getName().equals(optionSetName)) {
				optSet.get(i).updateOptionPrice(optionName, newPrice);
				return;
			}
		}
	}
	/**
	 * Delete data in OptionSet.
	 * @param name is the name of the data to be deleted.
	 */
	public void deleteOptionSet(String name) {
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).getName().equals(name)) {
				optSet.set(i,null);
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
		for(int i=0; i<optSet.size(); i++) {
			if(optSet.get(i).findOption(name) != null) {
				optSet.get(i).deleteOption(name);
				return;
			}
		}
		System.out.println(name + " is not found!");
	}
}
