package main.metamodel;

import java.util.HashMap;
import java.util.Map;

public class Transition {
	private String event;
	private State target;
	
    private HashMap<String, Integer> setters = new HashMap<String, Integer>();
    private HashMap<String, Integer> increments = new HashMap<String, Integer>();
    private HashMap<String, Integer> decrements  =new HashMap<String, Integer>();
    private HashMap<String, Integer> ifEqual = new HashMap<String, Integer>();
    private HashMap<String, Integer> ifLessThan = new HashMap<String, Integer>(); 
    private HashMap<String, Integer> ifGreaterThan = new HashMap<String, Integer>();

    public Transition (String event) {
    	this.event = event;
    }


	public Object getEvent() {
		return this.event;
	}

	public State getTarget() {
		return this.target;
	}
	
	public void setTarget(State target) {
		this.target = target;
	}

	public boolean hasSetOperation() {
		return setters.size() > 0;
	}

	public boolean hasIncrementOperation() {
		return increments.size() > 0;
	}

	public boolean hasDecrementOperation() {
		return decrements.size() > 0;
	}

	public Object getOperationVariableName() {
		Map.Entry<String, Integer> entry = null;
		if (setters.size() > 0) {
			entry = setters.entrySet().iterator().next();

		}
		if (increments.size() > 0) {
			entry = increments.entrySet().iterator().next();
			
		}
		if (decrements.size() > 0) {
			entry = decrements.entrySet().iterator().next();
		}
		
		return entry.getKey();
	}

	public boolean isConditional() {
		return ifEqual.size() > 0 || ifLessThan.size() > 0 || ifGreaterThan.size() > 0;
	}

	public Object getConditionVariableName() {
		Map.Entry<String, Integer> entry = null;
		if (ifEqual.size() > 0) {
			entry = ifEqual.entrySet().iterator().next();

		}
		if (ifLessThan.size() > 0) {
			entry = ifLessThan.entrySet().iterator().next();
			
		}
		if (ifGreaterThan.size() > 0) {
			entry = ifGreaterThan.entrySet().iterator().next();
		}
		return entry.getKey();
	}

	public Integer getConditionComparedValue() {
		Map.Entry<String, Integer> entry = null;
		if (ifEqual.size() > 0) {
			entry = ifEqual.entrySet().iterator().next();

		}
		if (ifLessThan.size() > 0) {
			entry = ifLessThan.entrySet().iterator().next();
			
		}
		if (ifGreaterThan.size() > 0) {
			entry = ifGreaterThan.entrySet().iterator().next();
		}
		return entry.getValue();
	}

	public boolean isConditionEqual() {
		Map.Entry<String, Integer> entry = null;
		if (ifEqual.size() > 0) 
			return true;

		return false;
	}

	public boolean isConditionGreaterThan() {
		if (ifGreaterThan.size() > 0) 
			return true;
		return false;
	}

	public boolean isConditionLessThan() {
		if (ifLessThan.size() > 0) 
			return true;
		return false;
	}

	public boolean hasOperation() {
		return setters.size() > 0 || increments.size() > 0 || decrements.size() > 0;
	}
	
	

	public HashMap<String, Integer> getIfGreaterThan() {
		return ifGreaterThan;
	}
	
	public HashMap<String, Integer> getIfLessThan() {
		return ifLessThan;
	}
	
	public HashMap<String, Integer> getIfEqual() {
		return ifEqual;
	}

	public HashMap<String, Integer> getDecrements() {
		return decrements;
	}

	public HashMap<String, Integer> getIncrements() {
		return increments;
	}

	public HashMap<String, Integer> getSetters() {
		return setters;
	}
	
	public void setSetter(String string, Integer i) {
		this.setters.put(string, i);
	}
	
	public void setIncrement(String string) {
			this.increments.put(string, 1);
	}
	
	public void setDecrement(String string) {
		this.decrements.put(string, -1);
	}
	
	public void setIfEqual(String string, Integer i) {
		this.ifEqual.put(string, i);
	}
	
	public void setIfGreaterThan(String string, Integer i) {
		this.ifGreaterThan.put(string, i);
	}
	
	public void setIfLessThan(String string, Integer i) {
		this.ifLessThan.put(string, i);
	}


	@Override
	public String toString() {
		return String.format("%s -> %s", this.event, this.target.getName());
	}
}
