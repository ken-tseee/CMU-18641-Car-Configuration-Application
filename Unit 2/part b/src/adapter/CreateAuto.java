package adapter;

public interface CreateAuto {
	public void BuildAuto(String filename) throws Exception;
	//Given a text file name a method called BuildAuto can be written to build an instance of 
	//Automobile. This method does not have to return the Auto instance.
	
	public void printAuto(String Modelname) throws Exception;
	//This function searches and prints the properties of a given Automodel.
}
