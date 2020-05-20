package jtm.activity05;

import java.util.Locale;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Ship extends Transport {
	
	protected byte sails;
	private String id;
	
	public Ship(String id, byte sails) {
		super(id, 0, 0);
		this.id = id;
		this.sails = sails;
		
	}

	
	public byte getSails() {
		return sails;
	}

	public void setSails(byte sails) {
		this.sails = sails;
	}
	

	@Override
	public String move(Road road) {
		
		if(road instanceof WaterRoad){
		 
			return String.format(Locale.US, "%s is sailing on %s with %d sails", getType(),road.toString(), sails);

		}else {
			
			return String.format(Locale.US, "Cannot sail on %s",road.toString());
		}
	}



	

	




	

}
