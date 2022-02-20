package main.metamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Machine {
	private List<State> states = new ArrayList<State>();
	private HashMap<String, Integer> integers = new HashMap<String, Integer>();
	private State initialState;
	
	public Machine(Collection<State> states, HashMap<String, Integer> integers,  State initial) {
		super();
		this.initialState = initial;
		this.integers = integers;
		this.states.addAll(states);
	}


	public List<State> getStates() {
		return states;
	}
	
	public HashMap<String, Integer> getIntegers() {
		return integers;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String string) {
		
		 List<State>result = states.stream().
				filter(x -> x.getName() == string)
				.collect(Collectors.toList());
		return result.get(0);
	}

	public int numberOfIntegers() {
		
		return integers.size();
	}

	public boolean hasInteger(String string) {
		if( integers.containsKey(string)){
			return true;
		}
			
		return false;
	}

}
