package jtm.activity06;

import java.util.ArrayList;

public class Martian implements Humanoid, Alien, Cloneable {

	private int weight = 0;
	Object vomit = null;
	ArrayList<Object> stomach = new ArrayList<Object>();

	public Martian(int weight2, ArrayList<Object> stomach2, String alive, Object vomit2) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Implementation of Object clone() method for Cloneable interface
	 * @see https://docs.oracle.com/javase/7/docs/api/java/lang/Cloneable.html
	 */
	
	@Override
    public Object clone() throws CloneNotSupportedException {
    return clone(this);
}
	
	//TODO implement cloning of current object
	//and it's stomach
	private Object clone(Object current) throws CloneNotSupportedException{
		Object result = null;
		if(current instanceof Martian) {
			Martian currentMartian = (Martian) current;
			Martian newMartian = new Martian(currentMartian.getWeight(),currentMartian.stomach,currentMartian.isAlive(),currentMartian.vomit());
		    newMartian.stomach = clone(currentMartian.stomach);
		    result = newMartian;
		}else if(current instanceof Human) {
			Human currentHuman = (Human)current;
			result = new Human(currentHuman.getWeight(),currentHuman.vomit(),currentHuman.isAlive(),currentHuman.vomit());
		}else if(current instanceof Integer) {
			result = new Integer(((Integer)current).intValue());
		}
		return result;
	}

	@Override
	public void eat(Object item) {
		if(item instanceof Human) {
			
			weight =((Human) item).getWeight();
			stomach.add(item);
			((Human) item).killHimself();
			
		}else if(item instanceof Martian) {
			weight = ((Martian)item).getWeight();
			stomach.add(item);
		}
	}

	@Override
	public void eat(Integer food) {
		if (food instanceof Integer) {
			stomach.add(food);
			weight = getWeight();
			}
	}

	@Override
	public Object vomit() {

		if (stomach instanceof Integer) {
			Integer vomintAmnt = (Integer)stomach;
		} else {
			ArrayList<Object> vomit = stomach;
			stomach.clear();
		}

		return vomit;
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
		return getClass().getSimpleName() + ": " + weight + " " + stomach;
	}

	@Override
	public int getWeight() {
		weight = Alien.BirthWeight + weight;
		return weight;
	}

}
