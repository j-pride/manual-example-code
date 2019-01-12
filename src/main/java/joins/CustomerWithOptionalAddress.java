package joins;

import java.sql.SQLException;

import pm.pride.JoinRecordDescriptor;
import pm.pride.RecordDescriptor;
import quickstart.Customer;

import static joins.JoinsClient.ADDRESS_ALIAS;
import static joins.JoinsClient.CUSTOMER_ADDRESS_JOIN_CONDITION;
import static joins.JoinsClient.CUSTOMER_ALIAS;

public class CustomerWithOptionalAddress extends Customer {
	Address privateAddress;

	public CustomerWithOptionalAddress() {}

	public CustomerWithOptionalAddress(int id) throws SQLException {
		super(id);
	}
	
	public Address getPrivateAddress() {
		return privateAddress;
	}

	public void setPrivateAddress(Address privateAddress) {
		this.privateAddress = privateAddress;
	}

    public static RecordDescriptor red =
    		new JoinRecordDescriptor(Customer.red, CUSTOMER_ALIAS)
    			.leftJoin(Address.red, ADDRESS_ALIAS, "privateAddress", CUSTOMER_ADDRESS_JOIN_CONDITION);
    		
    public RecordDescriptor getDescriptor() { return red; }
	
	public String toString() {
		String addressString = (privateAddress != null) ?
				", " + privateAddress.getStreet() + ", " + privateAddress.getCity() :
				" without address";
		return getFirstName() + " " + getName() + addressString;
				
	}
    
}
