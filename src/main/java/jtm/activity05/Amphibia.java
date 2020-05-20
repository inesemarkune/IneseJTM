package jtm.activity05;

import java.util.Locale;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Amphibia extends Transport {
	
	private String id;
	private byte sails;
	private int wheels;
	
	public Amphibia(String id, float consumption, int tankSize, byte sails, int wheels) {
		super(id, consumption, tankSize);
		this.sails = sails;
		this.wheels = wheels;
		this.id = id;
	}

	

	@Override
	public String move(Road road) {
		
	
		float minFuel = (this.getConsumption() * road.getDistance()) / 100;
		
		if(getFuelInTank() >= minFuel && road.getClass() == Road.class){
			setFuelInTank(getFuelInTank() - minFuel);
			
			 
			return String.format(Locale.US, "%s is driving on %s with %d wheels", getType(),road.toString(), wheels);

		}else{
			return String.format(Locale.US, "%s is sailing on %s with %d sails", getType(),road.toString(), sails);
		}
	}
	
}




