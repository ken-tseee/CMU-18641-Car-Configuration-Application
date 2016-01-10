/**
 * @author Junjian Xie
 */
package model;

import java.io.Serializable;

public class OptionSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Option[] opt;
	/**
	 * Initialize an object of OptionSet.
	 * @param name is a String.
	 * @param optionSetSize is an integer.
	 */
	protected OptionSet(String name, int optionSetSize) {
		this.name = name; 
		opt = new Option[optionSetSize]; 
	}
	/**
	 * Initialize an object of Option.
	 * @param name is a String.
	 * @param price is an float.
	 */
	protected void setOption(int n, String name, float price) {
		opt[n] = new Option(name);
		opt[n].price = price;
	}
	/**
	 * Print data in particular format.
	 */
	protected void print(){
		System.out.println(name+": " );
		for(int i = 0;i<opt.length;i++){
			if(opt[i]!=null) {
			opt[i].print();
			}
		}
		System.out.println("");
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	protected String getName() {
		return name;
	}
	/**
	 * Find the Option with names.
	 * @param name is a String that may be the same with an element.
	 * @return option is the one to be found.
	 */
	protected Option findOption(String name) {
		for(int i=0; i<opt.length; i++) {
			if(opt[i].name.equals(name)) {
				System.out.println("The result is " + opt[i].getName() + " and $" + opt[i].price + ".");
				return opt[i];
			}
		}
		return null;
	}
	/**
	 * Update data in Option with names.
	 * @param name1 is an element in the array.
	 * @param name2 is a new element for name1.
	 */
	protected void updateOption(String name1, String name2) {
		for(int i=0; i<opt.length; i++) {
			if(opt[i].name.equals(name1)) {
				opt[i].name = name2;
				return;
			}
		}
	}
	/**
	 * Update the price in Option by finding its name.
	 * @param name is an element in the array.
	 * @param newPrice is a new element for name.
	 */
	protected void updateOption(String name, float newPrice) {
		for(int i=0; i<opt.length; i++) {
			if(opt[i].name.equals(name)) {
				opt[i].price = newPrice;
				return;
			}
		}
	}
	/**
	 * Delete data in Option.
	 * @param name is the name of the data to be deleted.
	 */
	protected void deleteOption(String name) {
		for(int i=0; i<opt.length; i++) {
			if(opt[i].name.equals(name)) {
				opt[i] = null;
				return;
			}
		}
	}
	
	public class Option implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private float price = 0;
		
		protected Option(String name) {
			this.name = name;
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
