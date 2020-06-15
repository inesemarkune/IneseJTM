package jtm.activity09;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/*- TODO #2
 * Implement Iterator interface with Orders class
 * Hint! Use generic type argument of iterateable items in form: Iterator<Order>
 * 
 * TODO #3 Override/implement public methods for Orders class:
 * - Orders()                — create new empty Orders
 * - add(Order item)            — add passed order to the Orders
 * - List<Order> getItemsList() — List of all customer orders
 * - Set<Order> getItemsSet()   — calculated Set of Orders from list (look at description below)
 * - sort()                     — sort list of orders according to the sorting rules
 * - boolean hasNext()          — check is there next Order in Orders
 * - Order next()               — get next Order from Orders, throw NoSuchElementException if can't
 * - remove()                   — remove current Order (order got by previous next()) from list, throw IllegalStateException if can't
 * - String toString()          — show list of Orders as a String
 * 
 * Hints:
 * 1. To convert Orders to String, reuse .toString() method of List.toString()
 * 2. Use built in List.sort() method to sort list of orders
 * 
 * TODO #4
 * When implementing getItemsSet() method, join all requests for the same item from different customers
 * in following way: if there are two requests:
 *  - ItemN: Customer1: 3
 *  - ItemN: Customer2: 1
 *  Set of orders should be:
 *  - ItemN: Customer1,Customer2: 4
 */

//Implement Iterator interface with Orders class
public class Orders implements Iterator<Order> {

	private int currentOrder; // index of current order in the order list
	List<Order> orders;
	/*-
	 * TODO #1
	 * Create data structure to hold:
	 *   1. some kind of collection of Orders (e.g. some List)
	 *   2. index to the current order for iterations through the Orders in Orders
	 *   Hints:
	 *   1. you can use your own implementation or rely on .iterator() of the List
	 *   2. when constructing list of orders, set number of current order to -1
	 *      (which is usual approach when working with iterateable collections).
	 */

	// create new empty Orders
	public Orders() {
		orders = new LinkedList<Order>();
		currentOrder = -1;
	}

	// add passed order to the Orders
	public void add(Order item) {
		orders.add(item);
	}

	// List of all customer orders
	public List<Order> getItemsList() {
		return orders;
	}

	// calculated Set of Orders from list (look at description below)
	public Set<Order> getItemsSet() {

		Set<Order> orderSet = new LinkedHashSet<Order>();
		Order previousOrder = null;
		sort();
		for (Order order : orders) {
			if (previousOrder == null) {
				previousOrder = order;
				continue;
			}
			if (order.name.equals(previousOrder.name)) {
				previousOrder.count += order.count;
				previousOrder.customer += "," + order.customer;
			} else {
				orderSet.add(previousOrder);
				previousOrder = order;
			}
		}
		if (previousOrder != null) {
			orderSet.add(previousOrder);
		}

		// to make set sorted, create a new one from the existing one
		return new LinkedHashSet<Order>(orderSet);

	}

	public void sort() {
		Collections.sort(orders);
	}

	public void remove() {

		if (currentOrder >= 0 && currentOrder < orders.size()) {
			orders.remove(currentOrder);
			currentOrder--;
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public String toString() {

		return orders.toString();
	}

	@Override
	public boolean hasNext() {

		if (orders.size() > 0 && currentOrder < orders.size()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Order next() {
		if (hasNext()) {
			currentOrder++;
			return orders.get(currentOrder);
		} else {
			throw new NoSuchElementException();
		}
	}
}
