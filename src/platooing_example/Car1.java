package platooing_example;

import framework.CompBehavior;
import framework.Component;
import framework.State;

class Car1 extends Component {
	
	Car1(){
		super();
		this.c_behavior = new CompBehavior();
		State s_ctrl_throttle = new State("ctrl_throttle");
		State s_ctrl_brake = new State("ctrl_brake");
		State s_off = new State("off");
		
		c_behavior.add_state(s_ctrl_throttle);
		c_behavior.add_state(s_ctrl_brake);
		c_behavior.add_state(s_off);
		c_behavior.add_transition(new Transition(s_ctrl_brake,new Event()));
	}

	@Override
	public void refined_run() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void refined_setup() {
		// TODO Auto-generated method stub
		
	}

}
