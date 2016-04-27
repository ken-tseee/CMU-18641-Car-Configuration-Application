package adapter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import exception.AutoException;
import exception.ErrorType;
import model.Automobile;
import util.FileIO;

public abstract class proxyAutomobile implements CreateAuto, UpdateAuto {
	private Automobile a1;
	private LinkedHashMap <Integer,Automobile>lhsMap = new LinkedHashMap<Integer,Automobile>();

	@Override
	public void BuildAuto(String filename) {
		FileIO fio = new FileIO();
		a1 = fio.buildAutomotiveObject(filename);
	//	a1.print();
		lhsMap.put(lhsMap.size(), a1);
	}

	@Override
	public void printAuto(String Modelname) {
		try {
			Iterator<Entry<Integer, Automobile>> lit = lhsMap.entrySet().iterator();
			while(lit.hasNext()) {
				Entry<Integer, Automobile> e = (Entry<Integer, Automobile>) lit.next();
				if(e.getValue().getModel().equals(Modelname)) {
					e.getValue().print();
					return;
				}
				throw new AutoException(ErrorType.NO_MODELNAME.ordinal(), "Exception: Model is not found!");
			}
		} catch(AutoException ae) {
			AutoException.log(ae);
			Modelname = ae.fix(ErrorType.NO_MODELNAME.ordinal());
			printAuto(Modelname);
		}
	}
	/*
	public void printAuto(String Modelname) {
		if(a1.getName().equals(Modelname)) {
			a1.print();
		}
		else
			System.out.println(Modelname + " is not found!");
	}*/
	
	@Override
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
		try {
			Iterator<Entry<Integer, Automobile>> lit = lhsMap.entrySet().iterator();
			while(lit.hasNext()) {
				Entry<Integer, Automobile> e = (Entry<Integer, Automobile>) lit.next();
				if(e.getValue().getModel().equals(Modelname)) {
					e.getValue().updateOptionSet(OptionSetname, newName);
					return;
				}
				throw new AutoException(ErrorType.NO_OPTIONSETNAME.ordinal(), "Exception: OptionSet is not found!");
			}
		} catch(AutoException ae) {
			AutoException.log(ae);
			OptionSetname = ae.fix(ErrorType.NO_OPTIONSETNAME.ordinal());
			updateOptionSetName(Modelname, OptionSetname, newName);
		}
	}
	/*
		public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
		if(a1.getName().equals(Modelname)) {
			a1.updateOptionSet(OptionSetname, newName);
		}
		else
			System.out.println(Modelname + " is not found!");
	}*/
	
	@Override
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) {
		try {
			Iterator<Entry<Integer, Automobile>> lit = lhsMap.entrySet().iterator();
			while(lit.hasNext()) {
				Entry<Integer, Automobile> e = (Entry<Integer, Automobile>) lit.next();
				if(e.getValue().getModel().equals(Modelname)) {
					e.getValue().updateOptionPrice(Optionname, Option, newprice);
					return;
				}
				throw new AutoException(ErrorType.NO_OPTIONNAME.ordinal(), "Exception: Option is not found!");
			}
		} catch(AutoException ae) {
			AutoException.log(ae);
			Option = ae.fix(ErrorType.NO_OPTIONNAME.ordinal());
			updateOptionPrice(Modelname, Optionname, Option, newprice);
		}
	}
	/*
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) {
		if(a1.getName().equals(Modelname)) {
			a1.updateOption(Optionname, Option, newprice);
		}
		else
			System.out.println(Modelname + " is not found!");
	}
	*/
	
	public void setOptionhoice(String setName, String optionName) {
		a1.setOptionChoice(setName, optionName);
	}
	
	public void printChoice() {
		a1.printChoice();
	}
	
	public float getTotalPrice() {
		return a1.getTotalPrice(); 
	}
	
	public float getOptionChoicePrice(String setName) {
		return a1.getOptionChoicePrice(setName);
	}
}
