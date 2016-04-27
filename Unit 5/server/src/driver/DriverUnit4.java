package driver;

import java.io.IOException;

import adapter.BuildAuto;

public class DriverUnit4 {
	public static void main(String[] args) throws IOException {
		BuildAuto a = new BuildAuto();
		a.buildAuto("data/Prius.properties", "properties");
	}
}
