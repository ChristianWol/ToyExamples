package framework;

import java.util.LinkedList;

public class CompIF {

	LinkedList<String> var_list_in;
	LinkedList<String> var_list_out;
	
	public CompIF(){
		
	}
	
	public void add_in(String name){
		this.var_list_in.add(name);
	}
	
	public void add_out(String name){
		this.var_list_out.add(name);
	}
	
	
}
