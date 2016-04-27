package exception;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Fix1to5 implements FixAuto {
	public String fix(int errNum) {
		switch(errNum) {
		case 0:
			return fix1();
		case 1:
			return fix2();
		case 2:
			return fix3();
		case 3:
			return fix4();
		case 4:
			return fix5();
		default:
			System.out.println("Unknown error!");
			break;
		}
		return null;
	}
	/*
	public String fix1() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileName = null;
		try {
			fileName = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public String fix2() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String basePrice = null;
		try {
			basePrice = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return basePrice;
	}
	
	public String fix3() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String optionSetName = null;
		try {
			optionSetName = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return optionSetName;
	}
	
	public String fix4() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String optionName = null;
		try {
			optionName = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return optionName;
	}
	
	public String fix5() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String price = null;
		try {
			price = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return price;
	}
	*/
	public String fix1() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter("\n");
		System.out.println("Please input a correct file directory:"); 
		String fileName = s.nextLine();
		System.out.println();
		return fileName;
	}
	
	public String fix2() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter("\n");
		System.out.println("Please input a base price:"); 
		String basePrice = s.nextLine();
		System.out.println();
		return basePrice;
	}
	
	public String fix3() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter("\n");
		System.out.println("Please input the missing name of the OptionSet:"); 
		String optionSetName = s.nextLine();
		System.out.println();
		return optionSetName;
	}
	
	public String fix4() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter("\n");
		System.out.println("Please input the missing name of the Option:"); 
		String optionName = s.nextLine();
		System.out.println();
		return optionName;
	}
	
	public String fix5() {
		Scanner s = new Scanner(System.in);
		s.useDelimiter("\n");
		System.out.println("Please input the missing price of the Option:"); 
		String optionPrice = s.nextLine();
		System.out.println();
		return optionPrice;
	}
}
