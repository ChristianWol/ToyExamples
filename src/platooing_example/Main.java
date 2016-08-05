package platooing_example;
import framework.*;


class Main {

	public static void main(String args[]){
		
		// Declare components
		EnvSim envsim = new EnvSim();
		Car1 car1 = new Car1();
		Car2 car2 = new Car2();
		
		// Add components to testbed
		SpecTestBed testbed = new SpecTestBed();
		testbed.add_component(envsim);
		testbed.add_component(car1);
		testbed.add_component(car2);
		
		// Add logger to components
		envsim.set_logger(testbed.get_logger());
		car1.set_logger(testbed.get_logger());
		car2.set_logger(testbed.get_logger());
				
		// execute test cases and print report
		TestReport test_report = testbed.execute();
		test_report.print_to_stdout();
		
	}
	
}
