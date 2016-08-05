package platooing_example;

import framework.*;

public class SpecTestBed extends TestBed {

	private int loop_ctr = 0;
	@Override
	public boolean run_test_case_change_env() {
		boolean continue_test_case = true;
		return continue_test_case;
	}

	@Override
	public void setup_components_change_env() {
		this.test_env.assign_var("ticks", new Long(tick_ctr));
	}

	
	
}
