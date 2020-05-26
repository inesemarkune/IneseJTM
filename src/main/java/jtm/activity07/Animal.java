package jtm.activity07;

public class Animal {
	
	private int age;

	

	public void setAge(int age) {
		
		//this.age = age;
		if(age < 0) {
			this.age = 0;
		}else{
			this.age = age;
		}
	}
	
	public int getAge() {
		return age;
	}
	
}
