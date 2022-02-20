package main.metamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

interface Consumer{
	boolean evaluateVariable(String variable, int condition);
}

public class State {
	private final Map<String, Integer> integers;
	private String name;
	private List<Transition> transitions = new ArrayList<>();
	
	public State(String name, Map<String, Integer> integers) {
		super();
		this.name = name;
		this.integers = integers;
	}
	public String getName() {
		return this.name;
	}
	
	public void addTransition(Transition transition) {
		transitions.add(transition);
	}

	public List<Transition> getTransitions() {
		return this.transitions;
	}

	public Transition getTransitionsByEvent(String string) {
		var t = transitions.stream().
				filter(x -> x.getEvent().equals(string)).collect(Collectors.toList());

		return transitions.stream().
				filter(x -> x.getEvent().equals(string)).
				filter(x -> !x.isConditional() || (x.isConditional() && this.passes(x)))
				.findFirst().orElse(null);
	}

	public boolean passes(Transition transition) {
		if(!every(transition.getIfEqual(), (variable, condition) -> this.integers.get(variable) == condition))
			return false;

		if(!every(transition.getIfGreaterThan(),(variable, condition) -> this.integers.get(variable) > condition))
			return false;

		if(!every(transition.getIfLessThan(),(variable, condition) -> this.integers.get(variable) < condition))
			return false;

		return true;

	}

	public boolean every(Map<String, Integer> map , Consumer consumer) {

		for (Map.Entry<String, Integer> entry: map.entrySet()) {
			if(!consumer.evaluateVariable(entry.getKey(), entry.getValue()))
				return false;
		}

		return true;

	}

}
