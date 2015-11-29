package simulation;
import java.util.Scanner;
public class RunSimulation {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimulationValues myCustomerService = new SimulationValues();
		Scanner input = new Scanner(System.in);
		myCustomerService.setValues(input);
		myCustomerService.run();
	}
}
