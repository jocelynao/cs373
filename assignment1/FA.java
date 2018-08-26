package assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Iterator;

public class FA{

	private boolean[] isAccept = new boolean[1001];
	//check if state is accept or reject by indexing state number and seeing
	// if its true
	private ArrayList<Integer> acceptStates = new ArrayList<Integer>();
	// arraylist of acceptstates
	private HashSet<String> rejects = new HashSet<String>();
	// map of where the string is rejected if the string cannot be read by the
	// fa
	private boolean reject = false;
	// See if the string just stops because there is no transition for the input on a particular
	// state
	private HashSet<String> accepts = new HashSet<String>();
	// map of where the list is accepted i df the string cannot be read by the 
	// fa
	private Map<String, ArrayList<String>> transitionsTab = new HashMap<String, ArrayList<String>>();
	// holds all the state transitions. Key of map is the state plus the input. Value
	// is an arraylist of all the state it transitions to. 
	// CurrentState = 90
	// Input = 0
	// States it transitions to = {3, 5, 1}
	// Map entry = {900, {3, 5, 1}}
 	private ArrayList<ArrayList<String>> configs = new ArrayList<ArrayList<String>>();
 	// Holds all of the current states and input we still need to process. First value
 	// is the state. Second value will be the input string
 	// State = 9
 	// String = 010101
 	// ArrayList entry = {{9, 010101}}
	private String startState = "";

	public void configure(){
		while (configs.size() != 0){
			// System.out.println("Starting at beginning of config");
			String entry = configs.get(0).get(1);
			if (!(entry.equals(""))){
				String newEntry = entry.substring(1);
				String key = configs.get(0).get(0) + configs.get(0).get(1).charAt(0);
				// Getting the first input of the whole string to examine and getting 
				// the key (state and input) to find which state to transition to

				// System.out.println("key: " + key);
				if (transitionsTab.containsKey(key)){
					List<String> statesList = new ArrayList<>(transitionsTab.get(key));
					// Getting a list of all the transitions of the state we're currently at
					// given the input we have now
					// System.out.println("Stateslist: " + statesList);
					for (int y = 0; y < statesList.size(); y++){
						String newState = statesList.get(y);
						// System.out.println("Newstate: " + newState);
						// Iterating through all of the transitions

						if (newEntry.isEmpty()){
							if (isAccept[Integer.parseInt(newState)]){
								if (!(accepts.contains(newState))){
									accepts.add(newState);
								}
							}
							if (!(isAccept[Integer.parseInt(newState)]) &&
								accepts.size() == 0){
								// String newKey = newState + entry;	
								// System.out.println("newKey: " + newKey);				
								// if (!(transitionsTab.containsKey(newKey))){
								// 	reject = true;
								// }
								if (!(rejects.contains(newState))){
									rejects.add(newState);
								}
							}
						}

						// //If the input we have is currently empty, check if the transitioned state is an 
						// //accept or reject

						else{
						ArrayList<String> newConfig = new ArrayList<String>(
							Arrays.asList(statesList.get(y), newEntry));
						configs.add(newConfig);
						}

						// print();
						//If there's still input, make a new entry in the configures list with the 
						//new state we're at and the rest of the string that we still have to process

					}
				}
				else{

					// if (!(rejects.contains(configs.get(0).get(0)))){
					// 	rejects.add(configs.get(0).get(0));
					// }
					reject = true;
					// print();
				}
			}
			else{
				String curState = configs.get(0).get(0);
				if (isAccept[Integer.parseInt(curState)]){
					accepts.add(curState);
				}
				else{
					rejects.add(curState);
				}
			}
			configs.remove(0);
			// print();
		}
	}

	public void addAccept(String state){
		isAccept[Integer.parseInt(state)] = true;
		acceptStates.add(Integer.parseInt(state));
	}

	public void setStart(String s, String s1){
		startState = s;
		ArrayList<String> first = new ArrayList<String>(
			Arrays.asList(s, s1));
		configs.add(first);

	}

	public void setTransitions(String s, String tState){
		if (!transitionsTab.containsKey(s)){
			transitionsTab.put(s, new ArrayList<String>());
		}
		transitionsTab.get(s).add(tState);
		// System.out.println(transitionsTab.get(s));

		// System.out.println(tState);
		// System.out.println(transitionsTab.get(bState).size());
		// transitionsTab.put(s, Integer.parseInt(s2));
	}

	public void printResults(){
		if (accepts.size() > 0){
			System.out.print("accept ");
			for (String state : accepts){
				System.out.print(state + " ");
			}
			// System.out.print("\n");
		}
		else {
			System.out.print("reject ");
			if (rejects.size() > 0){
				for (String state : rejects){
					System.out.print(state + " ");
				}
			}	
			// System.out.println("\n");
		}
	}

	public void print(){
		System.out.println("\n---------------------------------\n");
		System.out.println("Start state: " + startState);
		System.out.println("Accept states: " + acceptStates);

		System.out.println("\nTransitions table: ");
		for (Map.Entry<String, ArrayList<String>> entry : transitionsTab.entrySet()){
			String s = entry.getKey();
			System.out.print(s.charAt(0) + " " + s.charAt(1) + " ");
			System.out.println(entry.getValue());
		}
		System.out.println("Size of table: " + transitionsTab.size());

		System.out.println("\nConfigs: ");
		for (int i = 0; i < configs.size(); i++){
			for (int y = 0; y < configs.get(i).size(); y++){
				System.out.print(configs.get(i).get(y) + ", ");
			}
			System.out.println("");
		}

		System.out.println("\n\nAccept results: " + accepts);
		System.out.println("Reject results: " + rejects);

		System.out.println("\n---------------------------------------\n");
	}

}