package joins;

import java.sql.SQLException;

import pm.pride.JoinRecordDescriptor;
import pm.pride.RecordDescriptor;
import pm.pride.SQL;
import quickstart.Customer;

public class CustomerWithCity extends Customer {
    protected static final RecordDescriptor red =
    	new JoinRecordDescriptor(CustomerWithCity.class, Customer.red, "c")
    	  .join("ADDRESS", "a", "a.customer_id = c.id")
    	    .row(Address.COL_CITY, "getCity", "setCity");

    public RecordDescriptor getDescriptor() { return red; }

    private String city;

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
    
	public CustomerWithCity(int id) throws SQLException {
		super(id);
	}
	
	public String toString() {
		return getFirstName() + " " + getName() + ", " + getCity();
	}
}
