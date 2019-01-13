package joins;

import java.sql.SQLException;

import pm.pride.JoinRecordDescriptor;
import pm.pride.RecordDescriptor;
import quickstart.Customer;
import static joins.JoinsClient.*;

public class CustomerWithCity extends Customer {
    protected static final RecordDescriptor red =
    	new JoinRecordDescriptor(CustomerWithCity.class, Customer.red, CUSTOMER_ALIAS)
    	  .join(Address.TABLE, ADDRESS_ALIAS, CUSTOMER_ADDRESS_JOIN_CONDITION)
    	    .row(Address.COL_CITY, "getCity", "setCity");

    public RecordDescriptor getDescriptor() { return red; }

    private String city;

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
    
	public CustomerWithCity(int id) throws SQLException {
		super(id);
	}
	
	public CustomerWithCity() {}
	
	public String toString() {
		return getFirstName() + " " + getName() + ", " + getCity();
	}
}
