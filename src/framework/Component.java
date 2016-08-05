package framework;

import java.util.concurrent.locks.Condition;

public abstract class Component implements Runnable{

	public boolean stopped_already = false;
	protected TestLogger test_logger;
	protected Thread this_thread;
	protected CompBehavior c_behavior;
	protected CompIF c_if;
	protected boolean stop_flag=false;
	protected TestEnvironment env;
	protected int last_ticks = 0;
	private String name;
	private Condition cond;
	
	
	protected Component(String name){
		this.name = name;
	}
	
	public synchronized void set_logger(TestLogger test_logger){
		this.test_logger = test_logger;
	}
	
	public synchronized void setup(TestEnvironment env){
		this.env = env;
		refined_setup();
		this_thread = new Thread(this);
		this_thread.start();
	}
	

    public synchronized void run() {
        System.out.println(Thread.currentThread().getId() + " Hello from a thread!");
        this.refined_run();
        stopped_already = true;
        this.notifyAll();
    }
    
   abstract public void refined_run();
   abstract public void refined_setup();

	public synchronized void stop() {
		System.out.println("stop");
		this.stop_flag = true;		
	}

	public /*synchronized*/ boolean executed_in_tick(long tick_ctr) {
		System.out.println(this.name+ " - ticks:"+last_ticks + " tick_ctr:"+tick_ctr);
		return (last_ticks == tick_ctr);
	}
}
