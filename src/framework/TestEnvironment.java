package framework;

import java.util.HashMap;

public abstract class TestEnvironment{

	HashMap<String,Object> env_value;
	HashMap<String,String> env_type;

	public TestEnvironment(){
		
		
	}
	
	void set_type(String var, String type){
		if (env_type.get(var) != null && !env_type.get(var).equals(type)){
			
		}
		
		env_type.put(var,  type);
	}
	
	void set_value(String var, Object o){
		env.put(key, value);
	}

	
	
	
}
