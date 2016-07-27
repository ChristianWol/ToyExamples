package framework;

public class Transition {

	public State old_state;
	public State new_state;
	public Event event;
	public double probability;
	
	public Transition(State old_state, Event event, State new_state){
		this.old_state = old_state;
		this.new_state = new_state;
		this.event = event;
	}
	
	public Transition(State old_state, Event event, State new_state, double probability){
		this(old_state, event, new_state);
		this.probability = probability;
		
		
	}
}
