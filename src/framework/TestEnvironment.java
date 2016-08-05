package framework;

import java.util.HashMap;

public class TestEnvironment{

	HashMap<String, java.lang.Class<Object>> type_mapping;
	HashMap<String,Object> value_mapping;
	
	public TestEnvironment(){
		type_mapping = new HashMap<String, java.lang.Class<Object>>();
		value_mapping = new HashMap<String,Object>();
		
	}
	
	public void init_var(String var, java.lang.Class<Object> type){
		if (type_mapping.get(var) != null && !type_mapping.get(var).equals(type)){
			System.out.println("Fatal Error - type mismatch");
			System.exit(-1);
		}
		
		type_mapping.put(var, type);
	}
	
	public void assign_var(String var, Object o){
		this.value_mapping.put(var,  o);
	}
	
	public Object read_var(String var){
		return this.value_mapping.get(var);
	}
	
	
}
