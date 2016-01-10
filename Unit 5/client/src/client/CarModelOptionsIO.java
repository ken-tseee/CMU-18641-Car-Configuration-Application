/**
 * @author Junjian Xie
 */
package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Properties;
import java.util.Scanner;

import model.Automobile;

public class CarModelOptionsIO {
	/**
	 * Build a Properties object.
	 * @param fileName
	 * @return
	 */
	public Properties buildProperties(String fileName) {
		try {
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(fileName);
			prop.load(in);
			prop.list(System.out);
			return prop;
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
