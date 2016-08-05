package platooing_example;

import framework.*;

/**
 * Car1 is the car behind Car2.
 */
class Car1 extends Component {
	
	Car1(){
		super("Car1");
		c_behavior = new CompBehavior();
		State s_ctrl_throttle = new State("ctrl_throttle");
		State s_ctrl_brake = new State("ctrl_brake");
		State s_off = new State("off");
		
		c_behavior.add_state(s_ctrl_throttle);
		c_behavior.add_state(s_ctrl_brake);
		c_behavior.add_state(s_off);
		c_behavior.add_transition(new Transition(s_ctrl_throttle,new Event("short_distance"),s_ctrl_brake));
		c_behavior.add_transition(new Transition(s_ctrl_throttle,new Event("senosr failure"),s_off, 0.000001));
		c_behavior.add_transition(new Transition(s_ctrl_brake,new Event("distance acceptable"),s_ctrl_throttle));
		c_behavior.add_transition(new Transition(s_ctrl_brake,new Event("senosr failure"),s_off, 0.000001));
	}

	@Override
	public void refined_run() {
		// TODO Auto-generated method stub
		do {
			int ticks = ((Long) this.env.read_var("ticks")).intValue();
			//System.out.println("ticks1:"+ticks+" last_ticks:"+last_ticks);
			if (ticks != last_ticks) {
				last_ticks = ticks;
				double dist = ((Double) this.env.read_var("sensor_car1_dist")).doubleValue();
				double speed = ((Double) this.env.read_var("sensor_car1_speed")).doubleValue();
				if (dist < (speed/2)) {
					this.env.assign_var("actuator_car2_brake",new Double(0.0)); // no breaking
					this.env.assign_var("actuator_car2_throttle", new Double(1.0));// causes deceleration
				} else if (dist <= speed) {
					this.env.assign_var("actuator_car2_throttle", new Double(0.0));// causes constant speed
				} else if (dist > speed && dist < 1.5*speed) {
					this.env.assign_var("actuator_car2_throttle", new Double(-1.0));
				}  else if (dist > speed) {
					this.env.assign_var("actuator_car2_brake",new Double(3.5));	
					this.env.assign_var("actuator_car2_throttle", new Double(-1.0));	
				}
			}
		
			Thread.yield();
		} while (!stop_flag);
		
	}

	@Override
	public void refined_setup() {
		last_ticks = 0;
	}

}
