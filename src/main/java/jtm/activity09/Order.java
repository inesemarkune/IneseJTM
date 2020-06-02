package jtm.activity09;

/*
 * Hints:
 * 1. When comparing orders, compare their values in following order:
 *    - Item name
 *    - Customer name
 *    - Count of items
 * If item or customer is closer to start of alphabet, it is considered "smaller"
 * 
 * 2. When implementing .equals() method, rely on compareTo() method, as for sane design
 * .equals() == true, if compareTo() == 0 (and vice versa).
 * 
 * 3. Also Ensure that .hashCode() is the same, if .equals() == true for two orders.
 * 
 */

//Implement Comparable interface with Order class
// * Hint! Use generic type of comparable items in form: Comparable<Order>
public class Order implements Comparable<Order> {
	String customer; // Name of the customer
	String name; // Name of the requested item
	int count; // Count of the requested items

	// - public Order(String orderer, String itemName, Integer count) —
	// constructor of the Order
	public Order(String orderer, String itemName, Integer count) {
		this.customer = orderer;
		this.name = itemName;
		this.count = count;
	}

	// - public int compareTo(Order order) — comparison implementation according
	// to logic described below
	@Override
	public int compareTo(Order order) {

		if (name.compareTo(order.name) < 0) {
			return -1;
		} else if (name.compareTo(order.name) > 0) {
			return 1;
		} else if (customer.compareTo(order.customer) < 0) {
			return -1;
		} else if (customer.compareTo(order.customer) > 0) {
			return 1;
		} else if (count < order.count) {
			return -1;
		} else if (count > order.count) {
			return 1;
		} else {
			return 0;
		}
	}

	// - public boolean equals(Object object) — check equality of orders
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (!(object == null) && (object instanceof Order)) {

			Order order = (Order) object;

			if (name == order.name && customer == order.customer && count == order.count) {
				return true;
			} else {

				return false;
			}
		}
		return result;
	}

	// - public int hashCode() — to be able to handle it in some hash...
	// collection
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	// - public String toString() — string in following form: "ItemName:
	// OrdererName: Count"
	@Override
	public String toString() {
		return name + ": " + customer + ": " + count;
	}
}
