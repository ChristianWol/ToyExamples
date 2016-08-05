package framework;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class TestBed {
	
	private LinkedList<Component> comp_list;
	private ArrayList<TestCase> tc_list;
	private TestLogger test_logger;
	private TestReport test_report;
	protected TestEnvironment test_env;
	
	protected long tick_ctr = 0;
	
	public abstract void setup_components_change_env();

	// Change the environment to influence the system
	// return true: continue, return false: stop
	public abstract boolean run_test_case_change_env();
	
	
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
		this.test_env = new TestEnvironment();
		 setup_components_change_env();
		for (Component comp: this.comp_list){
			System.out.println("setup");
			comp.setup(this.test_env);
		}
		
	}
		
	private void run_test_case(){
		System.out.println("run_tc");
		boolean continue_test_case = true;
		System.out.println("run_tc");
		do {
			System.out.println("run_tc");
			boolean executed_in_current_tick = true;
			for (Component comp: this.comp_list){
				System.out.println("run_tc");
				executed_in_current_tick = executed_in_current_tick && comp.executed_in_tick(tick_ctr);
			}
			if (executed_in_current_tick || tick_ctr==0){
				System.out.println("assign tick:"+tick_ctr);
				tick_ctr++;
				this.test_env.assign_var("ticks", new Long(tick_ctr)); // TODO: Das hier könnte böse für die Performanz sein.
			}
			continue_test_case = run_test_case_change_env();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.yield();
		} while (continue_test_case);
	}
	
	private void close_test_case(){
		System.out.println("close begin");
		ArrayList<Component> reversed_list = new ArrayList<Component>();
		for (Component comp: this.comp_list){
			reversed_list.add(comp);
		}
		Collections.reverse(reversed_list);
		
		for (Component comp: reversed_list){
			System.out.println("close comp");
			comp.stop();
			
			try {
				System.out.println("sync comp");
				synchronized (comp){
					if (comp.stopped_already = false){
						System.out.println("begin wait");
						comp.wait();
						System.out.println("end wait");
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("closed comps");
		
	}

	public TestReport execute() {

		boolean coverage_sufficient = false;
		int ctr = 0;
		while (!coverage_sufficient && ctr < MAX_TC_CONST){
			ctr++;
			setup_components();
			run_test_case();
			close_test_case();
			coverage_sufficient = analyze_for_completeness();
		}
		
		
		return this.test_report;
	}

	private boolean analyze_for_completeness() {
		// TODO Auto-generated method stub
		return true;
	}

}
