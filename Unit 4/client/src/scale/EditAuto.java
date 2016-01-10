/**
 * @author Junjian Xie
 */
package scale;

public interface EditAuto {
	public float getOptionPrice(String modelName, String setName, String option);
	//Get the price of an Option.
	public String getOptionName(String modelName, String setName, String option);
	//Get the name of an Option.
	public void editOptionPrice(String modelName, String setName, String option, float newPrice);
	//Edit the price of an Option.
	public void editOptionName(String modelName, String setName, String option, String newName);
	//Edit the Name of an Option.
	public void printOptionPrice(String modelName, String setName, String option);
	//Print the price of an Option.
	public void printOptionName(String modelName, String setName, String option);
	//Print the Name of an Option.
}
