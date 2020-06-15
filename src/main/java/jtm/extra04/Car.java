package jtm.extra04;

import java.util.Locale;

public class Car {

	private String model;
	private Integer year;
	private String color;
	private Float price;

	// TODO #1 generate public constructor which takes all properties of an
	// object as parameters

	public Car(String model, Integer year, String color, Float price) {
		super();
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
	}
// TODO #2 generate public getters of all object properties

	public String getModel() {
		return model;
	}

	public Integer getYear() {
		return year;
	}

	public String getColor() {
		return color;
	}

	public Float getPrice() {
		return price;
	}
/*
	@Override
	public String toString() {
		return "{\"model\":\"" + this.getModel() + "\", \"year\":" + this.getYear() + ",\"color\":\"" + this.getColor()
				+ "\",\"price\":" + String.format(Locale.US, "%.0f", this.getPrice()) + "}";

	}
*/
}
