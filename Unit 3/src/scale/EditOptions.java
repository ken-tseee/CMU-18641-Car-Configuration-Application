/**
 * @author Junjian Xie
 */
package scale;

public class EditOptions implements Runnable {
	private EditAuto auto;
	private String modelName;
	private String setName;
	private String option;
	/**
	 * Constructor
	 * @param auto
	 * @param modelName
	 * @param setName
	 * @param option
	 */
	public EditOptions(EditAuto auto, String modelName, String setName, String option) {
		this.auto = auto;
		this.modelName = modelName;
		this.setName = setName;
		this.option = option;
	}
	/**
	 * Override the run method which edits the price.
	 */
	/*
	@Override
	public void run() {
		float price = auto.getOptionPrice(modelName, setName, option); 
		long id = Thread.currentThread().getId();
		
		synchronized(auto) {
			for(int i=0; i<5; i++) {
				++price;
				auto.editOptionPrice(modelName, setName, option, price);
				System.out.println(i+1 + ":\nThread " + id + " edited the price!");
				auto.printOptionPrice(modelName, setName, option);
				System.out.println();
			}
			System.out.println("Thread " + id + " has finished!" + "\nThe price is $"
			 + auto.getOptionPrice(modelName, setName, option));
			System.out.println();
		}
	}
	*/
	/**
	 * The following part is NOT dead codes! As the two run methods cannot appear at
	 * the same time, either should be commented!
	 */
	
	/**
	 * Override the run method which edits the name.
	 */
	
	@Override
	public void run() { 
		long id = Thread.currentThread().getId();
		String[] a = {"a", "b", "c", "d", "present"};
		
		synchronized(auto) {
			for(int i=0; i<5; i++) {
				auto.editOptionName(modelName, setName, option, a[i]);
				option = a[i];
				System.out.println(i+1 + ":\nThread " + id + " edited the name!");
				auto.printOptionName(modelName, setName, option);
				System.out.println();
			}
			System.out.println("Thread " + id + " has finished!" + "\nThe name is "
			 + auto.getOptionName(modelName, setName, option));
			System.out.println();
		}
	}
}
