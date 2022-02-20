package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;




public class MachineInterpreter {
	private Machine machine;
	private State state;
	private HashMap<String, Integer> integers = new HashMap<String, Integer>();
	

	public void run(Machine m) {;
		this.machine = m;
		this.state = m.getInitialState();
		this.integers = m.getIntegers();
	}

	public State getCurrentState() {
		return this.state;
	}

	public void processEvent(String string) {
		Transition transition = this.state.getTransitionsByEvent(string);
		if(transition == null) {
			return;
		}
			
		this.processOperation(transition);
		this.state = transition.getTarget();
	}

	public int getInteger(String string) {
		return this.integers.get(string);
	}
	

	public void processOperation(Transition transition) {
		if(!transition.hasOperation()) {
			return;
		}
		
		for (Entry<String, Integer> entry : transition.getSetters().entrySet()) {
			this.integers.put(entry.getKey(), entry.getValue());
		}
		
		for (Entry<String, Integer> entry : transition.getIncrements().entrySet()) {
			int current = integers.get(entry.getKey());
			this.integers.put(entry.getKey(), current + entry.getValue());
		}
		for (Entry<String, Integer> entry : transition.getDecrements().entrySet()) {
			int current = integers.get(entry.getKey());
			this.integers.put(entry.getKey(), current + entry.getValue());
		}
	}


	
}