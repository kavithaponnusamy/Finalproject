package co.grandcircus.FinalProject.entity;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class State {
	@JsonProperty("MyArray") 
    public List<String> myArray;

	public List<String> getMyArray() {
		return myArray;
	}

	public void setMyArray(List<String> myArray) {
		this.myArray = myArray;
	}

	@Override
	public String toString() {
		return "State [myArray=" + myArray + "]";
	}

	

}
