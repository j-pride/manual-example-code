package joins;

import static joins.JoinsClient.ADDRESS_ALIAS;
import static joins.JoinsClient.CUSTOMER_ADDRESS_JOIN_CONDITION;
import static joins.JoinsClient.CUSTOMER_ALIAS;

import java.sql.SQLException;


import pm.pride.JoinRecordDescriptor;
import pm.pride.RecordDescriptor;
import quickstart.Customer;

public class CustomerWithAddress extends Customer {
	Address address;

	public CustomerWithAddress() {}

	public CustomerWithAddress(int id) throws SQLException {
		super(id);
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

    public static RecordDescriptor red =
    		new JoinRecordDescriptor(Customer.red, CUSTOMER_ALIAS)
    			.join(Address.red, ADDRESS_ALIAS, "address", CUSTOMER_ADDRESS_JOIN_CONDITION);
    		
    public RecordDescriptor getDescriptor() { return red; }
	
	public String toString() {
		return getFirstName() + " " + getName() + ", " + address.getStreet() + ", " + address.getCity();
				
	}
    
}
