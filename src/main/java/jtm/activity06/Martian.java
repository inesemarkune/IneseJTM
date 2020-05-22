package jtm.activity06;

import java.util.ArrayList;

public class Martian implements Humanoid, Alien, Cloneable {

	private ArrayList<Object>stomach = new ArrayList<Object>();
	private Object item;
	private int food;
	private int eatenFood;
	private int weight;
	private boolean isAlive = true;
	
	public Martian() {
		super();
		stomach = null;
		weight = Alien.BirthWeight;
		isAlive = true;
	}
	
	@Override
	public void eat(Object item) {
		if(stomach.isEmpty()) {
			stomach.add(item);
			weight +=(int)item;
			eatenFood = (int)item;
		}
	}

	@Override
	public void eat(Integer food) {
		if(stomach.isEmpty()) {
			stomach.add(food);
			weight = weight + food;
			eatenFood = food;
		}
	}

	@Override
	public Object vomit() {
		
		//sorry,this is horrible :D
		
		
		
		
		int vomit = 0;
		
		if(stomach.contains(item)||stomach.contains(food)){
			stomach.remove(item);
			stomach.remove(food);
			weight = weight - (food + (int)item);
			vomit = food + (int)item;
			return vomit;
		}else{
			return vomit;
		}
		
	}

	@Override
	public String isAlive() {
		
		return "I AM IMMORTAL!";
	}

	@Override
	public String killHimself() {
		
		return "I AM IMMORTAL!";
	}

	@Override
	public String toString() {
		return "Martian: " + getWeight()+  " [" + stomach + "]";
	}

	@Override
	public int getWeight() {
		int BirthWeight = -1;
		int currentWeight = BirthWeight + weight;
		return currentWeight;
	}

}
