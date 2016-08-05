package platooing_example;

import framework.*;

public class EnvSim  extends Component {
	
	private double car1_speed;
	private double car1_posx;
	private double car2_speed;
	private double car2_posx;
	
	private int ticks;
	
	private void update_physics(){
		
		int delta_t_ms = (ticks-last_ticks);

		car1_posx += car1_speed*delta_t_ms/1000;
		car2_posx += car2_speed*delta_t_ms/1000;
		
		// Read values
		double brake_force_car1 = ((Double) this.env.read_var("actuator_car1_brake")).doubleValue();
		double throttle_change_car1 = ((Double) this.env.read_var("actuator_car1_throttle")).doubleValue();
		
		double brake_force_car2 = ((Double) this.env.read_var("actuator_car2_brake")).doubleValue();
		double throttle_change_car2 = ((Double) this.env.read_var("actuator_car2_throttle")).doubleValue();
		
		
		// Compute new values
		double sensor_car1_speed = car1_speed + 0.15*car1_speed*throttle_change_car1 - brake_force_car1 * delta_t_ms/1000;
		double sensor_car2_speed = car2_speed + 0.15*car2_speed*throttle_change_car2 - brake_force_car2 * delta_t_ms/1000;
		
		double sensor_car1_dist = car2_posx-car1_posx;
		double sensor_car2_dist = car2_posx-car1_posx;
		
		System.out.println(ticks+" "+sensor_car1_speed +" "+ sensor_car2_speed+" "+ sensor_car1_dist+ " " + car1_posx + " " + car2_posx);
		
		// Assign new values
		this.env.assign_var("sensor_car1_dist",new Double(sensor_car1_dist));
		this.env.assign_var("sensor_car1_speed",new Double(sensor_car1_speed));
		
		this.env.assign_var("sensor_car2_dist",new Double(sensor_car2_dist));
		this.env.assign_var("sensor_car2_speed",new Double(sensor_car2_speed));
		
	}
	
	EnvSim(){
		super("EnvSim");
	}

	@Override
	public void refined_run() {
		// TODO Auto-generated method stub
		do {
			ticks = ((Long) this.env.read_var("ticks")).intValue();
			//System.out.println("ticks3:"+ticks+" last_ticks:"+last_ticks);
			if (last_ticks != ticks){
				update_physics();
				last_ticks = ticks;
			}

			Thread.yield();
		} while (!stop_flag);
		
	}

	@Override
	public void refined_setup() {
		last_ticks = 0;
		
//		car1_speed = 20;
//		car1_posx = 70;
//		car2_speed = 23;
//		car2_posx = 35;
		
//		car1_speed = 50;
//		car1_posx = 70;
//		car2_speed = 23;
//		car2_posx = 35;
		
		car1_speed = 50;
		car1_posx = 35;
		car2_speed = 23;
		car2_posx = 50;
		
		this.env.assign_var("actuator_car1_brake",new Double(0.0));
		this.env.assign_var("actuator_car1_throttle",new Double(0.0));
		this.env.assign_var("actuator_car2_brake",new Double(0.0));
		this.env.assign_var("actuator_car2_throttle",new Double(0.0));
		
		this.update_physics();
	}

	
	
	
}
