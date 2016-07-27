package framework;


public abstract class Component implements Runnable{

	TestLogger test_logger;
	Thread this_thread;
	CompBehavior c_behavior;
	CompIF c_if;
	boolean stop_flag=false;
	
	
	public synchronized void set_logger(TestLogger test_logger){
		this.test_logger = test_logger;
	}
	
	public synchronized void setup(){
		refined_setup();
		this_thread = new Thread(this);
		this_thread.start();
	}
	

    public void run() {
        System.out.println("Hello from a thread!");
        this.test_logger.notify();
        this.refined_run();
    }
    
   abstract public void refined_run();
   abstract public void refined_setup();

	public synchronized void stop() {
		this.stop_flag = true;
		
	}
}
