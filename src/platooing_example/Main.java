package platooing_example;
import framework.*;


class Main {

	public static void main(String args[]){
		
		Car1 car1 = new Car1();
		Car2 car2 = new Car2();
		TestBed testbed = new TestBed();
		testbed.add_component(car1);
		testbed.add_component(car2);
		car1.set_logger(testbed.get_logger());
		car2.set_logger(testbed.get_logger());
		
		TestEnvironment env = new TestEnvironment();
		
		
		TestReport test_report = testbed.execute();
		
		test_report.print_to_stdout();
		
	}
	
}
