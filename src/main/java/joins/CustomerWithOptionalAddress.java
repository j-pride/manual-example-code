package joins;

import java.sql.SQLException;

import pm.pride.JoinRecordDescriptor;
import pm.pride.RecordDescriptor;
import quickstart.Customer;

import static joins.JoinsClient.ADDRESS_ALIAS;
import static joins.JoinsClient.CUSTOMER_ADDRESS_JOIN_CONDITION;
import static joins.JoinsClient.CUSTOMER_ALIAS;

public class CustomerWithOptionalAddress extends Customer {
	Address address;

	public CustomerWithOptionalAddress() {}

	public CustomerWithOptionalAddress(int id) throws SQLException {
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
    			.leftJoin(Address.red, ADDRESS_ALIAS, "address", CUSTOMER_ADDRESS_JOIN_CONDITION);
    		
    public RecordDescriptor getDescriptor() { return red; }
	
	public String toString() {
		String addressString = (address != null) ?
				", " + address.getStreet() + ", " + address.getCity() :
				" without address";
		return getFirstName() + " " + getName() + addressString;
				
	}
    
}
