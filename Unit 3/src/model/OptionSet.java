/**
 * @author Junjian Xie
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;
import exception.ErrorType;

public class OptionSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Option> opt;
	private Option choice;
	/**
	 * Initialize an object of OptionSet.
	 * @param name is a String.
	 * @param optionSetSize is an integer.
	 */
	protected OptionSet(String optSetName) {
		this.name = optSetName; 
		opt = new ArrayList<Option>(); 
	}
	/**
	 * Initialize an object of Option.
	 * @param name is a String.
	 * @param price is an float.
	 */
	protected void setOption(String name, float price) {
		try {
			if(name.equals("") || name.equals(" ")) {
				throw new AutoException(ErrorType.NO_OPTIONNAME.ordinal(), "Exception: The Option name is not found!");
			}
		} catch(AutoException ae) {
			AutoException.log(ae);
			name = ae.fix(ErrorType.NO_OPTIONNAME.ordinal());
		} finally {
		opt.add(new Option(name, price));
		}
	}
	/**
	 * Print data in particular format.
	 */
	protected void print(){
		System.out.println(name+": " );
		for(int i = 0;i<opt.size();i++){
			if(opt.get(i)!=null) {
				opt.get(i).print();
			}
		}
		System.out.println("");
	}
	
	protected void printChoice() {
		System.out.println(choice.name + ": $" + choice.price);
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	protected String getName() {
		return name;
	}
	/**
	 * Set the choice with a particular name.
	 * @param nameChoice
	 */
	protected void setChoice(String nameChoice) {
		choice = findOption(nameChoice);
	}
	/**
	 * Get the choice in this OptionSet.
	 * @return
	 */
	protected Option getChoice() {
		if(choice != null) {
			return choice;
		}
		return choice;
	}
	/**
	 * Get the name of the choice.
	 * @return
	 */
	protected String getOptionChoiceName() {
		return choice.name;
	}
	/**
	 * Get the price of the choice.
	 * @return
	 */
	protected float getOptionChoicePrice(String setname) {
		if(choice.name.equals(setname)) {
			return choice.price;
		}
		return 0;
	}
	
	/**
	 * Find the Option with names.
	 * @param name is a String that may be the same with an element.
	 * @return option is the one to be found.
	 */
	protected Option findOption(String name) {
		for(int i=0; i<opt.size(); i++) {
			if(opt.get(i).name.equals(name)) {
		//		System.out.println("The result is " + opt.get(i).getName() + " and $" + opt.get(i).price + ".");
				return opt.get(i);
			}
		}
		return null;
	}
	
	public float findOptionPrice(String option) {
		for(int i=0; i<opt.size(); i++) {
			if(opt.get(i).name.equals(option)) {
				return opt.get(i).getPrice();
			}
		}
		return 0;
	}
	
	public String findOptionName(String option) {
		for(int i=0; i<opt.size(); i++) {
			if(opt.get(i).name.equals(option)) {
				return opt.get(i).getName();
			}
		}
		return null;
	}
	/**
	 * Update data in Option with names.
	 * @param name1 is an element in the array.
	 * @param name2 is a new element for name1.
	 */
	protected void updateOptionName(String name, String newName) {
		for(int i=0; i<opt.size(); i++) {
			if(opt.get(i).name.equals(name)) {
				opt.get(i).name = newName;
				return;
			}
		}
	}
	/**
	 * Update the price in Option by finding its name.
	 * @param name is an element in the array.
	 * @param newPrice is a new element for name.
	 * @throws AutoException 
	 */
	protected void updateOptionPrice(String name, float newPrice) throws AutoException {
		for(int i=0; i<opt.size(); i++) {
			if(opt.get(i).name.equals(name)) {
				opt.get(i).price = newPrice;
				return;
			}
		}
		throw new AutoException(ErrorType.NO_OPTIONNAME.ordinal(), "Exception: The Option name is not found");
	}
	/**
	 * Delete data in Option.
	 * @param name is the name of the data to be deleted.
	 */
	protected void deleteOption(String name) {
		for(int i=0; i<opt.size(); i++) {
			if(opt.get(i).name.equals(name)) {
				opt.set(i,null);
				return;
			}
		}
	}
	
	protected class Option implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private float price = 0;
		
		protected Option(String name, float price) {
			this.name = name;
			this.price = price;
		}

		protected float getPrice() {
			return price;
		}

		protected void setPrice(float price) {
			this.price = price;
		}

		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}
		/**
		 * Print data in particular format.
		 */
		protected void print(){
			System.out.println(name +": $" + price );
		}
	}
}
