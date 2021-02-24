package com.cfa.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


class AutoCompleteStates {

    /*
        For the given text, return the names of the States that are considered matches.
        There should be no duplicates in the collection.
      A State is a match if it meets one of the following conditions.  Results should be returned
      in the priority listed below where one is highest priority and three is lowest.  If two matches
      are the same priority, those matches should be sorted alphabetically.
      1. Match exact abbreviation (Should be case insensitive)
      2. Match start of state name
      3. Match any part of state name
    */
    public List<String> filterStates(String textEntered) {
    	  
    	  //if text entered is null or empty empty list will be returned.
    	  List<String> finalStateList = new ArrayList<String>();
    	  if (textEntered == null || textEntered.trim().isEmpty()) {
    		  return finalStateList;
    	  }
    	
      	  Map<String,String> abbrAndNames = new HashMap<String, String>();
      	  
      	  Set<String> matchedStates = new LinkedHashSet<String>();
      	  List<String> abbrevations = new ArrayList<String>();
      	  List<String> names = new ArrayList<String>();
      	  
      	  for (State state : State.values()) {
      		  abbrAndNames.put(state.getAbbreviation(), state.getStateName());
      		  names.add(state.getStateName());
      		  abbrevations.add(state.getAbbreviation());
      	  }
      	  
      	  for (int i = 0; i < State.values().length; i++) {
      		  if(abbrevations.contains(textEntered)) {
      			  //Adding the state name matched with abbreviation
      			  String macthedState = abbrAndNames.get(textEntered);
      			  matchedStates.add(macthedState);
      			  
  				  //Adding the states with names stating with the given key.
      			  List<String> secondList = names.stream().filter(x -> x.regionMatches(true, 0,
      					textEntered, 0, 2)).collect(Collectors.toList());
      			  
      			  matchedStates.addAll(secondList.stream().sorted().collect(Collectors.toList()));
      			  
      			  //Adding the states with substring match with the given key.
      			  List<String> thirdList = new ArrayList<String>();
      			  for(String name : names) {
      				  if(!matchedStates.contains(name)) {
      					  if(name.indexOf(textEntered.toLowerCase(), 1) != -1) {
      						  thirdList.add(name);
      					  }
      				  }
      			  }
      			  matchedStates.addAll(thirdList.stream().sorted().collect(Collectors.toList()));
      	      } else {
      	    	  /*If text entered does not match the any abbreviation key, 
      	    	  /empty list will be returned*/
      	    	return finalStateList;
      	      }
  		  }  
      	  
      	  
      	  finalStateList.addAll(matchedStates);
      	  
      	  return finalStateList;
    }

}
