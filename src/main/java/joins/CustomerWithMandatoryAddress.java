package joins;

import static joins.JoinsClient.ADDRESS_ALIAS;
import static joins.JoinsClient.CUSTOMER_ADDRESS_JOIN_CONDITION;
import static joins.JoinsClient.CUSTOMER_ALIAS;

import java.sql.SQLException;


import pm.pride.JoinRecordDescriptor;
import pm.pride.RecordDescriptor;
import quickstart.Customer;

public class CustomerWithMandatoryAddress extends Customer {
	Address privateAddress;

	public CustomerWithMandatoryAddress() {}

	public CustomerWithMandatoryAddress(int id) throws SQLException {
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
    			.join(Address.red, ADDRESS_ALIAS, "privateAddress", CUSTOMER_ADDRESS_JOIN_CONDITION);
    		
    public RecordDescriptor getDescriptor() { return red; }
	
	public String toString() {
		return getFirstName() + " " + getName() + ", " + privateAddress.getStreet() + ", " + privateAddress.getCity();
				
	}
    
}
