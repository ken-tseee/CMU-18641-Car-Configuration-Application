package client;

import java.util.Scanner;

import model.Automobile;

public class SelectCarOption {
	/**
	 * Users select the preferences.
	 * @param auto
	 */
	public void selectCarOption(Automobile auto) {
		Scanner scanner = new Scanner(System.in);
		String userInput;
		auto.print();
	
		for(int i=0; i<auto.getOptionSetSize(); ++i) {
			System.out.println("Please input your choice of "
		+ auto.getOptionSetName(i) + ":");
			userInput = scanner.nextLine();
			while(userInput.equals("") || userInput.equals(" ")) {
				System.out.println("Please input your choice of "
						+ auto.getOptionSetName(i) + ":");
				userInput = scanner.nextLine();
			}
			auto.setOptionChoice(auto.getOptionSetName(i), userInput);
		}
		auto.printChoice();
		System.out.println("Total price is: $" + auto.getTotalPrice() + "\n");
	}
}
