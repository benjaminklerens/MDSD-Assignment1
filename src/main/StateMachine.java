package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.metamodel.Transition;
import main.metamodel.*;
import main.metamodel.Machine;

public class StateMachine {
	private Map<String,State> states = new HashMap<>();
	private HashMap<String, Integer> integers = new HashMap<>();
	private State current;
	private State initial;
	
	private State getState(String name) {
		if(!states.containsKey(name))
			states.put(name, new State(name, integers));
		return states.get(name);
	}
	
	public Machine build() {
		return new Machine(states.values(), integers, initial);
	}

	public StateMachine state(String string) {
		this.current = getState(string);
		return this;
	}

	public StateMachine initial() {
		initial = this.current;
		return this;
	}
	
	public State lastState() {
		return this.states.get(this.current.getName());
	}
	
	public Transition lastTransition() {
		return this.lastState().getTransitions().get(this.lastState().getTransitions().size() - 1);
	}

	public StateMachine when(String name) {
		this.lastState().addTransition(new Transition(name));
		return this;
	}

	public StateMachine to(String string) {
		if(!states.containsKey(string)){
			states.put(string, new State(string, integers));
		} 
		State target = this.states.get(string);
		this.lastTransition().setTarget(target);
			
		return this;
	}

	public StateMachine integer(String string) {
		if(!integers.containsKey(string))
			integers.put(string, 0);
	
		return this;
	}

	public StateMachine set(String string, int i) {
		this.lastTransition().setSetter(string, i);
		//integers.put(string, i);
		return this;
	}

	public StateMachine increment(String string) {
		this.lastTransition().setIncrement(string);
		// integers.put(string, integers.get(string) + 1 );
		return this;
	}

	public StateMachine decrement(String string) {
		this.lastTransition().setDecrement(string);
		// integers.put(string, integers.get(string) - 1 );
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		this.lastTransition().setIfEqual(string, i);
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		this.lastTransition().setIfGreaterThan(string, i);
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		this.lastTransition().setIfLessThan(string, i);
		return this;
	}

}
