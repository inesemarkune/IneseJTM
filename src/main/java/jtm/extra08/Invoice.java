package jtm.extra08;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO Annotate class as an @Entity, to say that its instances can be stored in the database.
@Entity
public class Invoice {

	// TODO Annotate id field as an @Id, to mark it as a primary key field.
	@Id
	private Integer id;

	// TODO Annotate items field as @OneToMany(mappedBy = "invoice") to note
	// that current object is related to many Item objects using "invoice" field
	// of Item as a mapping value
	// Annotate items field with @OrderBy("id ASC") annotation to note that set
	// of them
	// should be sorted
	@OneToMany(mappedBy = "invoice")
	@OrderBy("id ASC")
	private Set<Item> items;

	// TODO add annotation @Temporal(TemporalType.TIMESTAMP) to generate
	// timestamp for invoice date
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Invoice() {
		// TODO initialize some sorted Set of items, when invoice is created
		items = new LinkedHashSet<Item>();
	}

	public void addItem(Item item) {
		// TODO add passed item to the items set
		items.add(item);
	}

	public Integer getId() {
		// TODO return requested value
		return id;
	}

	public void setId(Integer id) {
		// TODO save passed value into invoice
		this.id = id;
	
	}

	// TODO annotate method with @OneToMany(mappedBy = "invoice") to define
	// relation between invoice and items
	@OneToMany(mappedBy = "invoice")
	public Set<Item> getItems() {
		// TODO return required value
		return items;
	}

	// TODO annotate method with @PrePersist to define that method should be
	// called before saving data
	@PrePersist
	protected void onPrePersist() {
		// TODO set date field to current system date
		date = new Date(); 
	}

	public Float getTotalAmount() {
		// TODO return total price of all the items in the invoice
		float totalAmount = 0;
		Item item = new Item();
		
		for(int i = 0; i < items.size(); i++) {
			totalAmount += item.getPrice() * item.getCount();
		}
		return totalAmount;
	}

	public Integer getItemNo() {
		// TODO return number of items in invoice
		Item item = new Item();
		return item.getCount();
	}

	@Override
	// Do not change this method, it is implemented already
	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
		String dateString = df.format(date);
		return "'Invoice:" + id + ", date:" + dateString + ", total amount:" + getTotalAmount() + ", items:"
				+ getItemNo() + "\n\t" + getItems();
	}

}
