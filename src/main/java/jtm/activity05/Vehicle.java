package jtm.activity05;

import java.util.Locale;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Vehicle extends Transport {
	
	protected int wheels;
	//private String id;

	public Vehicle(String id, float consumption, int tankSize, int wheels) {
		super(id, consumption, tankSize);
		this.wheels = wheels;
		//this.id = id;
		
	}
	
	
	@Override
	public String move(Road road) {
		
		float minFuel = (this.getConsumption() * road.getDistance()) / 100;
		
		
		 
		if(getFuelInTank() >= minFuel && road.getClass() == Road.class){
			setFuelInTank(getFuelInTank() - minFuel);
			
			 //ID Vehicle is driving on (Road as String) with x wheels
			return String.format(Locale.US, "%s is driving on %s with %d wheels", getType(),road.toString(), wheels);

		}else {
			
			return String.format(Locale.US, "Cannot drive on %s",road.toString());
		}
	}
	

	

}
