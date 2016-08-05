package framework;

import java.util.LinkedList;

public class CompBehavior {
	
	private LinkedList<State> state_list;
	private LinkedList<Transition> transition_list;

	public CompBehavior (){
		this.state_list = new LinkedList<State>();
		this.transition_list = new LinkedList<Transition>();
		
	}
	
	public void add_state(State s){
		this.state_list.add(s);
	}
	
	public void add_transition(Transition t){
		this.transition_list.add(t);
	}
	
}
