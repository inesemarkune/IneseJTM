package jtm.activity07;

public class Dog extends Mammal{

	private String name;
	
	//Mammal mammal = new Dog();

	public void setName(String name) {
		
		if(name == null || name.length() < 1 || !name.matches("[a-zA-Z]+")) {
			this.name = "";
		}else if (name.length() == 1) {
	        this.name = name.toUpperCase();
	    }else {
			this.name = name.substring(0,1).toUpperCase() + name.substring(1);

		}
	}
		
	
	
	public String getName() {
		return name;
	}
}
