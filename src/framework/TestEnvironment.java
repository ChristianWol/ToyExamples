package framework;

import java.util.HashMap;

public abstract class TestEnvironment{

	HashMap<String, java.lang.Class<Object>> type_mapping;
	HashMap<String,Object> value_mapping;
	
	public TestEnvironment(){
		
		
	}
	
	void init_var(String var, java.lang.Class<Object> type){
		if (type_mapping.get(var) != null && !type_mapping.get(var).equals(type)){
			System.out.println("Fatal Error - type mismatch");
			System.exit(-1);
		}
		
		type_mapping.put(var, type);
	}
	
	void assign_var(String var, Object o){
		this.value_mapping.put(var,  o);
	}
	
	
}
