package framework;

import java.util.*;


public class TestBed {
	
	private LinkedList<Component> comp_list;
	private ArrayList<TestCase> tc_list;
	private TestLogger test_logger;
	private TestReport test_report;
	
	long tick_ctr;
	
	
	// SECTION FOR CONSTANTS
	private int MAX_TC_CONST = 1000;
	
	public TestBed(){
		this.comp_list = new LinkedList<Component>();
		this.test_logger = new TestLogger();
		this.test_report = new TestReport();
	}

	public void add_component(Component comp) {
		this.comp_list.add(comp);
		
	}
	
	public void add_tc(TestCase tc){
		tc_list.add(tc);
	}

	public TestLogger get_logger() {
		return this.test_logger;
	}
	
	// Goal: start all components 
	// 
	// Start is in order of list and each start needs to be confirmed 
	private void setup_components() {
		for (Component comp: this.comp_list){
			comp.setup();
			try {
				this.test_logger.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void run_test_case(){
		tick_ctr++;
		for (Component comp: this.comp_list){
			comp.compute(tick_ctr, current_env, next_env);
			try {
				this.test_logger.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void close_test_case(){
		ArrayList<Component> reversed_list = new ArrayList<Component>();
		for (Component comp: this.comp_list){
			reversed_list.add(comp);
		}
		Collections.reverse(reversed_list);
		
		for (Component comp: reversed_list){
			comp.stop();
			try {
				this.test_logger.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public TestReport execute() {

		bool coverage_sufficient = false;
		int ctr = 0;
		while (!coverage_sufficient || ctr < MAX_TC_CONST){
			ctr++;
			TestEnvironment test_env = new TestEnvironment();
			setup_components();
			run_test_case();
			close_test_case();
			coverage_sufficient = analyze_for_completeness();
		}
		
		
		return this.test_report;
	}

}
