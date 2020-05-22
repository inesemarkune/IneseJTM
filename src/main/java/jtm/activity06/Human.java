package jtm.activity06;

import java.util.ArrayList;

public class Human implements Humanoid{

	private int stomach;
	private int food;
	private int eatenFood;
	private int weight;
	private boolean isAlive; 
	
	
	
	public Human() {
		super();
		stomach = 0;
		weight = BirthWeight;
		isAlive = true;
	}

	@Override
	public void eat(Integer food) {
		
		if(stomach == 0) {
			stomach += food;
			weight += food;
			eatenFood = food;
		}
	}

	@Override
	public Object vomit() {
		int vomit = stomach;
		if(stomach != 0) {
			weight -= stomach;
			stomach = 0;
		}
		
		return vomit;
	}

	@Override
	public String isAlive() {
			if(isAlive) {
			return "Alive";
			}else {
				return "Dead";
			}
		
	}

	@Override
	public String killHimself() {
			isAlive = false;
			return "Dead";
			
	}

	@Override
	public int getWeight() {
		int currentWeight = BirthWeight + stomach;
		
		return currentWeight;
	}

	@Override
	public String toString() {
		
		return "Human: " + getWeight() + " [" + stomach + "]";
	}

}
