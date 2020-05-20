package jtm.activity05;

import jtm.activity04.Road;

public class WaterRoad extends Road {

	
	public WaterRoad(String from, String to, int distance) {
		super(from, to, distance);
		// TODO Auto-generated constructor stub
	}

	public WaterRoad() {
	}
	
@Override
	public String toString() {
		
		//WaterRoad From — To, 00km
     
		return this.getClass().getSimpleName() + " " + super.toString();
	}
}
