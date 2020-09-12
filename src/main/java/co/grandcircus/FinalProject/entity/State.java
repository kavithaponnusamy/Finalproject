package co.grandcircus.FinalProject.entity;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class State {
	@JsonProperty("MyArray") 
    public List<String> states;

	public List<String> getMyArray() {
		return states;
	}

	public void setMyArray(List<String> states) {
		this.states = states;
	}

	@Override
	public String toString() {
		return "State [states=" + states + "]";
	}

	

}
