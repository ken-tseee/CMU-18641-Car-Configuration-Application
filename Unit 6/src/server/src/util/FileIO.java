/**
 * @author Junjian Xie
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import exception.AutoException;
import exception.ErrorType;
import model.Automobile;

public class FileIO {
	private String[] fl = new String [3];
	
	/**
	 * Serialization
	 * @param au is an instance of Automotive
	 * @return
	 */
	public Automobile serializeAutomobile(Automobile au) {
		Automobile staff = new Automobile();
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Automotive.dat"));
			out.writeObject(au);
			out.close();
			
			ObjectInputStream in =  new ObjectInputStream(new FileInputStream("Automotive.dat"));
			staff = (Automobile) in.readObject();
		} catch(Exception e) {  
			System.out.print("Error: " + e);
			System.exit(1);
		}
		return staff;
	}
	/**
	 * Build Automobile Object from a file.
	 * @param filename
	 * @return
	 */
	public Automobile buildAutomobileObject(String filename) {
		try {
			File file = new File(filename);
			try {
			if(!file.exists()) {
				throw new AutoException(ErrorType.NO_FILE.ordinal(), "Exception: File is not found!");
			}
		} catch (AutoException ae) {
			AutoException.log(ae);
			filename = ae.fix(ErrorType.NO_FILE.ordinal());
			try {
				return fileReading(new File(filename));
			} catch (IOException e) {
				System.out.println("Error" + e.toString());
				return null;
			}
		}
			return fileReading(file);
		} catch (IOException e) {
			System.out.println("Error" + e.toString());
			return null;
		}
	}
	/**
	 * Read a file to memory.
	 * @param file
	 * @return
	 * @throws IOException
	 */	
	public Automobile fileReading(File file) throws IOException { 
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		fl = firstLine(line);
		String model = fl[0].trim();
		float basePrice = 0;
		try {
			try {
				if(fl[1].equals("") || fl[1].equals(" ")) {
					throw new AutoException(ErrorType.NO_BASEPRICE.ordinal(), "Exception: Base price is not found!");
				}
			} catch (AutoException ae) {
				AutoException.log(ae);
				fl[0] = ae.fix(ErrorType.NO_BASEPRICE.ordinal()).trim();
			} finally {
				basePrice = Float.parseFloat(fl[1]);
			}
		} catch(NumberFormatException e) {
			System.out.println("Error-- " + e.toString());
		}
		Automobile au = new Automobile(model, basePrice);
		boolean eof = false;
		int n_optSet = 0;
		while(!eof) {
			line = br.readLine();
			if(line == null) {
				eof = true;
			}
			else {
				oneLine(line, n_optSet, au);
			}
			n_optSet++;
		}
		br.close();
		return au;
	}
	/**
	 * Deal with the first line.
	 * @param s
	 * @return
	 */
	public String[] firstLine(String s) {
		String[] st = s.split(",");
		if(st.length != 2) {
			return null;
		}
		fl = st;
		return fl;
	}
	/**
	 * Deal with one line for the remaining lines.
	 * @param s is a String
	 * @param n_opset is the No. of OptionSet
	 * @param au is an instance of Automotive.
	 */
	public void oneLine(String s, int n_opset, Automobile au) {
		String[] s_part = s.split(":");
		String optionSetName = s_part[0];
		String[] pair = s_part[1].split(",");
		int size_OptionSet = pair.length;
		au.setOptionSet(optionSetName);
		String[] word;
		for(int i=0; i<size_OptionSet; i++) {
			word = pair[i].split("\\$");
			try {
				if(word[1].equals(" ")) {
					throw new AutoException(ErrorType.NO_OPTIONPRICE.ordinal(), "Exception: Price is not found!");
				}
			} catch(AutoException ae) {
				AutoException.log(ae);
				word[1] = ae.fix(ErrorType.NO_OPTIONPRICE.ordinal());		
			} finally {
			au.setOption(n_opset, word[0].trim(), Float.parseFloat(word[1]));
			}
		}
	}
	/**
	 * Build an Automobile object from the properties file.
	 * @param fileName
	 * @throws IOException
	 */
	public void buildPropertiesObject(String fileName) throws IOException {
		Properties prop = new Properties();
		FileInputStream in = new FileInputStream(fileName);
		ArrayList<String> propList = new ArrayList<String>();
		String[] propName = {"CarMake", "CarModel", "Option1", "OptionValue1a",
				"OptionValue1b", "Option2", "OptionValue2a", "OptionValue2b"};
		prop.load(in);
		prop.list(System.out);
		System.out.println();
		
		String CarMake = prop.getProperty("CarMake");
		propList.add(CarMake);
		String CarModel = prop.getProperty("CarModel");
		propList.add(CarModel);
		String Option1 = prop.getProperty("Option1");
		propList.add(Option1);
		String OptionValue1a = prop.getProperty("OptionValue1a");
		propList.add(OptionValue1a);
		String OptionValue1b = prop.getProperty("OptionValue1b");
		propList.add(OptionValue1b);
		String Option2 = prop.getProperty("Option2");
		propList.add(Option2);
		String OptionValue2a = prop.getProperty("OptionValue2a");
		propList.add(OptionValue2a);
		String OptionValue2b = prop.getProperty("OptionValue2b");
		propList.add(OptionValue2b);
		
		for(int i=0; i<propList.size(); ++i) {
			System.out.println(propName[i] + ": " + propList.get(i));
		}
	}
}
